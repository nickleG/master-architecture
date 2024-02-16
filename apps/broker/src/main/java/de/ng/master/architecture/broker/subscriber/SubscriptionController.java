package de.ng.master.architecture.broker.subscriber;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SubscriptionController {

  private final SubscriptionService subscriptionService;

  @RequestMapping("/subscribe")
  public void subscribe(Subscriber subscriber) {
    log.info("Received subscription: {}", subscriber);
    subscriptionService.handleSubscription(subscriber);
  }
}
