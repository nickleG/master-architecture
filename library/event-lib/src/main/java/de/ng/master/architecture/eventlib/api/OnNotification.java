package de.ng.master.architecture.eventlib.api;

import de.ng.master.architecture.eventlib.events.Notification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface OnNotification {

  @PostMapping(path = "/subscription/Notification", consumes = "application/json")
  ResponseEntity<Void> onNotification(@RequestBody Notification message);
}
