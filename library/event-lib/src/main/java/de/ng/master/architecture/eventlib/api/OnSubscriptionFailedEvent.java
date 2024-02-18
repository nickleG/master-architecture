package de.ng.master.architecture.eventlib.api;

import de.ng.master.architecture.eventlib.events.SubscriptionFailedEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

public interface OnSubscriptionFailedEvent {

  @PostMapping(path = "/subscription/SubscriptionFailedEvent", consumes = "application/json")
  ResponseEntity<Void> onSubscriptionFailedEvent(SubscriptionFailedEvent message);
}
