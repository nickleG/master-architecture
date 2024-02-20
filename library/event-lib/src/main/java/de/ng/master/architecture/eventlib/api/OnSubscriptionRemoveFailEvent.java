package de.ng.master.architecture.eventlib.api;

import de.ng.master.architecture.eventlib.events.SubscriptionRemoveFailEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface OnSubscriptionRemoveFailEvent {

  @PostMapping(path = "/subscription/SubscriptionRemoveFailEvent", consumes = "application/json")
  ResponseEntity<Void> onSubscriptionRemoveFailedEvent(@RequestBody SubscriptionRemoveFailEvent message);

}
