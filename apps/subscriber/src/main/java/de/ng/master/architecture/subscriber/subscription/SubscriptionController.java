package de.ng.master.architecture.subscriber.subscription;

import de.ng.master.architecture.eventlib.events.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SubscriptionController {

  @PostMapping(path = "/subscription/Notification", consumes = "application/json")
  public void callBack(@RequestBody Notification message) {
    log.info("Received callback with message: {}", message);
  }
}
