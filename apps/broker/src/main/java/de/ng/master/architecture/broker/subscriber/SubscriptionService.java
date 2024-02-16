package de.ng.master.architecture.broker.subscriber;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscriptionService {

  SubscriptionRepository subscriptionRepository;

  public void handleSubscription(Subscriber subscriber) {
    log.info("Received subscription: {}", subscriber);
    subscriptionRepository.save(SubscriberMapper.map(subscriber));

  }
}
