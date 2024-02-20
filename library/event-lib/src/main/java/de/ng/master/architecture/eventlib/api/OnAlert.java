package de.ng.master.architecture.eventlib.api;

import de.ng.master.architecture.eventlib.events.Alert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface OnAlert {

  @PostMapping(path = "/subscription/Alert", consumes = "application/json")
  ResponseEntity<Void> onAlert(@RequestBody Alert message);
}
