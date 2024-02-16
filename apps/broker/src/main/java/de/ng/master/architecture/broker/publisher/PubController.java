package de.ng.master.architecture.broker.publisher;

import de.ng.master.architecture.broker.errorhandling.BadEventException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is the controller for the publisher. It is responsible for receiving events from publishers
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class PubController {

  private final PubService pubService;

  @PostMapping("/publish/{topic}")
  public ResponseEntity<Void> receiveEvent(@PathVariable String topic, @RequestBody String jsonContent) throws BadEventException {
    log.info("Received event -- topic:{}, content: {}", topic, jsonContent);
    SimpleEvent event = new SimpleEvent(topic, jsonContent);
    verifyEvent(event);
    pubService.handleEvent(event);
    return ResponseEntity.ok().build();
  }

  private void verifyEvent(SimpleEvent event) throws BadEventException {
    if (event.getTopic() == null || event.getTopic().isEmpty() ||
        event.getJsonContent() == null || event.getJsonContent().isEmpty()) {
      throw new BadEventException();
    }
  }

}
