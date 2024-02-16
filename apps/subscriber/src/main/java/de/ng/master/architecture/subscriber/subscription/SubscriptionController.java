package de.ng.master.architecture.subscriber.subscription;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SubscriptionController {

  private final SubscriptionClient client;

  @GetMapping("/subscribe")
  public void subscribe() {
    client.subscribe(new Subscription("http://localhost:8080/notification", Notification.class.getSimpleName()));
    log.info("Received subscription");
  }

  @PostMapping(path = "/notification", consumes = "application/json")
  public void callBack(@RequestBody Notification message) {
    log.info("Received callback with message: {}", message);
  }
}
