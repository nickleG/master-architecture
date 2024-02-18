package de.ng.master.architecture.broker.subscriber;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscriptionService {

  private final SubscriptionRepository subscriptionRepository;

  public ResponseEntity<Void> handleSubscription(Subscriber subscriber) {
    log.info("Received subscription: {}", subscriber);
    SubscriberEntity entity = SubscriberMapper.map(subscriber);
    subscriptionRepository.findSubscriberEntityByCallbackUrlAndTopic(subscriber.getCallbackUrl(), subscriber.getTopic())
        .ifPresentOrElse(
            existing -> log.info("Subscription already exists: {}", existing),
            () -> {
              log.info("Saving subscription: {}", entity);
              subscriptionRepository.save(entity);
            }
        );
    return ResponseEntity.ok().build();
  }
}
