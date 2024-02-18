package de.ng.master.architecture.endpoint;

import de.ng.master.architecture.eventlib.events.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class EventEndpoint {

  @PostMapping(path = "/subscription", consumes = "application/json")
  public void onNotification(@RequestBody Object message) {
    log.info("Received onNotification with message: {} = {}", message.getClass(), message);
  }

  @PostMapping(path = "/subscription/Notification", consumes = "application/json")
  public void onNot(@RequestBody Notification message) {
    log.info("Received onNotification with message: {} = {}", message.getClass(), message);
  }


}
