package de.ng.master.architecture.eventlib.api;

import de.ng.master.architecture.eventlib.events.SubscriptionEstablishedEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface OnSubscriptionEstablishedEvent {

  @PostMapping(path = "/subscription/SubscriptionEstablishedEvent", consumes = "application/json")
  ResponseEntity<Void> onSubscriptionEstablishedEvent(@RequestBody SubscriptionEstablishedEvent message);
}
