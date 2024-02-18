package de.ng.master.architecture.eventlib.api;

import de.ng.master.architecture.eventlib.events.Alert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

public interface OnAlert {

  @PostMapping(path = "/subscription/Alert", consumes = "application/json")
  ResponseEntity<Void> onAlert(Alert message);
}
