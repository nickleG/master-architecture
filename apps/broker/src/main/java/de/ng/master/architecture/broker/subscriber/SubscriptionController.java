package de.ng.master.architecture.broker.subscriber;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SubscriptionController {

  private final SubscriptionService subscriptionService;

  @PostMapping("/subscribe")
  public ResponseEntity<Void> subscribe(@RequestBody Subscriber subscriber) {
    log.info("Received subscription: {}", subscriber);
    return subscriptionService.handleSubscription(subscriber);
  }
}
