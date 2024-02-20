package de.ng.master.architecture.eventlib.api;

import de.ng.master.architecture.eventlib.events.SubscriptionFailedEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface OnSubscriptionFailedEvent {

  @PostMapping(path = "/subscription/SubscriptionFailedEvent", consumes = "application/json")
  ResponseEntity<Void> onSubscriptionFailedEvent(@RequestBody SubscriptionFailedEvent message);
}
