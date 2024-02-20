package de.ng.master.architecture.eventlib.api;

import de.ng.master.architecture.eventlib.events.SubscriptionRemovedEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface OnSubscriptionRemovedEvent {

  @PostMapping(path = "/subscription/SubscriptionRemovedEvent", consumes = "application/json")
  ResponseEntity<Void> onSubscriptionRemovedEvent(@RequestBody SubscriptionRemovedEvent message);
}
