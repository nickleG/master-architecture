package de.ng.master.architecture.broker.subscriber;

import de.ng.master.architecture.broker.publisher.SimpleEvent;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SubscriptionController {

  private final SubscriptionService subscriptionService;

  @PostMapping("/subscribe")
  public ResponseEntity<Void> subscribe(@RequestBody Subscription subscription) {
    log.info("Received subscription: {}", subscription);
    subscriptionService.handleSubscription(subscription);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/pull-events/{topic}")
  public ResponseEntity<List<SimpleEvent>> pullEvents(@PathVariable String topic) {
    List<SimpleEvent> simpleEvents = subscriptionService.pullEvents(topic);
    return ResponseEntity.ok(simpleEvents);
  }
}
