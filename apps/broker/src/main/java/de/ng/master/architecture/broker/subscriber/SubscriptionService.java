package de.ng.master.architecture.broker.subscriber;

import de.ng.master.architecture.broker.publisher.SimpleEvent;
import de.ng.master.architecture.broker.publisher.SimpleEventMapper;
import de.ng.master.architecture.broker.publisher.SimpleEventRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscriptionService {

  private final SubscriptionRepository subscriptionRepository;
  private final SimpleEventRepository simpleEventRepository;

  public void handleSubscription(Subscription subscription) {
    log.info("Received subscription: {}", subscription);
    subscriptionRepository.save(SubscriberMapper.map(subscription));

  }

  public List<SimpleEvent> pullEvents(String topic) {
    return simpleEventRepository.findSimpleEventEntitiesByNameAndPublishedIsFalse(topic).stream().map(e -> {
      e.setPublished(true);
      return simpleEventRepository.save(e);
    }).map(SimpleEventMapper::map).toList();
  }
}
