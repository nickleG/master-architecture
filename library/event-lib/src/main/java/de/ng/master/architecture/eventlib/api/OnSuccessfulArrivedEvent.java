package de.ng.master.architecture.eventlib.api;

import de.ng.master.architecture.eventlib.events.SuccessfulArrivedEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface OnSuccessfulArrivedEvent {

  @PostMapping(path = "/subscription/SuccessfulArrivedEvent", consumes = "application/json")
  ResponseEntity<Void> onSuccessfulArriveEvent(@RequestBody SuccessfulArrivedEvent message);
}
