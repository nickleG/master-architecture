package de.ng.master.architecture.broker.publisher;

import de.ng.master.architecture.broker.delivery.DeliveryEntity;
import de.ng.master.architecture.broker.delivery.DeliveryRepository;
import de.ng.master.architecture.broker.subscriber.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class PubService {

  private SimpleEventRepository eventRepo;
  private SubscriptionRepository subRepo;
  private DeliveryRepository deliveryRepo;

  /**
   * saves the event to the database and starts asynchronous processing in a new thread to return a response to the publisher as quickly as possible
   *
   * @param event
   */
  public void handleEvent(SimpleEvent event) {
    log.info("Saving event: {}", event);
    SimpleEventEntity entity = SimpleEventMapper.map(event);
    // save the event to the database
    SimpleEventEntity save = eventRepo.save(entity);
    // start asynchronous processing in a new thread
    new Thread(() -> {
      // do something with the event
      notifyAllSubscribers(save);
      log.info("Processing event: {}", event);
    }).start();
  }

  private void notifyAllSubscribers(SimpleEventEntity event) {
    // notify all subscribers
    log.info("Notifying all subscribers: {}", event);
    subRepo.findSubscriberEntitiesByTopic(event.getName())
        .forEach(subscriberEntity -> {
          // send the event to the subscriber
          log.info("Sending event to subscriber: {}", subscriberEntity);
          ResponseEntity<Void> voidResponseEntity = new RestTemplate().postForEntity(subscriberEntity.getCallbackUrl(), event.getJsonContent(), Void.class);
          if (voidResponseEntity.getStatusCode().is2xxSuccessful()) {
            log.info("Event sent successfully to subscriber: {}", subscriberEntity);
            DeliveryEntity build = DeliveryEntity.builder().subscriberId(subscriberEntity.getId()).eventId(event.getId()).delivered(true).build();
            deliveryRepo.save(build);
          } else {
            log.error("Failed to send event to subscriber: {}", subscriberEntity);
            DeliveryEntity build = DeliveryEntity.builder().subscriberId(subscriberEntity.getId()).eventId(event.getId()).delivered(false).build();
            deliveryRepo.save(build);
          }
        });

  }
}
