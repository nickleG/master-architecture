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
    subscriptionRepository.save(SubscriberMapper.map(subscriber));
    return ResponseEntity.ok().build();
  }
}
