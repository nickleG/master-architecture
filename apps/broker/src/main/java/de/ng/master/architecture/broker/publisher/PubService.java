package de.ng.master.architecture.broker.publisher;

import de.ng.master.architecture.broker.delivery.DeliveryClient;
import de.ng.master.architecture.broker.delivery.DeliveryEntity;
import de.ng.master.architecture.broker.delivery.DeliveryRepository;
import de.ng.master.architecture.broker.subscriber.SubscriberEntity;
import de.ng.master.architecture.broker.subscriber.SubscriptionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PubService {

  private final SimpleEventRepository eventRepo;
  private final SubscriptionRepository subRepo;
  private final DeliveryRepository deliveryRepo;
  private final DeliveryClient deliveryClient;

  /**
   * saves the event to the database and starts asynchronous processing in a new thread to return a response to the publisher as quickly as possible
   */
  public void handleEvent(SimpleEvent event) {
    log.debug("Saving event: {}", event);
    SimpleEventEntity entity = SimpleEventMapper.map(event);
    SimpleEventEntity save = eventRepo.save(entity);
    new Thread(() -> notifyAllSubscribers(save)).start();
  }

  private void notifyAllSubscribers(SimpleEventEntity event) {
    List<SubscriberEntity> subscribers = subRepo.findSubscriberEntitiesByTopic(event.getTopic());
    if (subscribers.isEmpty()) {
      log.warn("No subscribers found for event: {}", event);
    }
    subscribers
        .forEach(subscriberEntity -> {
          ResponseEntity<Void> voidResponseEntity = deliveryClient.deliver(event, subscriberEntity);
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
