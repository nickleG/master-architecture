package de.ng.master.architecture.service.controller;

import de.ng.master.architecture.eventlib.api.OnAlert;
import de.ng.master.architecture.eventlib.api.OnNotification;
import de.ng.master.architecture.eventlib.config.LibConfig;
import de.ng.master.architecture.eventlib.events.Alert;
import de.ng.master.architecture.eventlib.events.Notification;
import de.ng.master.architecture.eventlib.events.SuccessfulArrivedEvent;
import de.ng.master.architecture.eventlib.pubsub.client.PublishClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EventEndpoint implements OnAlert, OnNotification {

  private final PublishClient publishClient;
  private final LibConfig libConfig;

  @Override
  public ResponseEntity<Void> onAlert(Alert message) {
    log.info("Received onAlert with message: {}", message);
    publishClient.publish(new SuccessfulArrivedEvent(message.getUuid(), message.getClass().getSimpleName(), libConfig.getClientName()));
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<Void> onNotification(Notification message) {
    log.info("Received onNotification with message: {}", message);
    publishClient.publish(new SuccessfulArrivedEvent(message.getUuid(), message.getClass().getSimpleName(), libConfig.getClientName()));
    return ResponseEntity.ok().build();
  }
}
