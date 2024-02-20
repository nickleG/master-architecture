package de.ng.master.architecture.endpoint;

import com.vaadin.flow.component.notification.Notification.Position;
import de.ng.master.architecture.eventlib.api.OnAlert;
import de.ng.master.architecture.eventlib.api.OnNotification;
import de.ng.master.architecture.eventlib.api.OnSubscriptionEstablishedEvent;
import de.ng.master.architecture.eventlib.api.OnSubscriptionFailedEvent;
import de.ng.master.architecture.eventlib.api.OnSuccessfulArrivedEvent;
import de.ng.master.architecture.eventlib.events.Alert;
import de.ng.master.architecture.eventlib.events.Notification;
import de.ng.master.architecture.eventlib.events.SubscriptionEstablishedEvent;
import de.ng.master.architecture.eventlib.events.SubscriptionFailedEvent;
import de.ng.master.architecture.eventlib.events.SuccessfulArrivedEvent;
import de.ng.master.architecture.services.UiUpdateService;
import de.ng.master.architecture.views.eventoverview.EventOverviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class EventEndpoint implements OnAlert, OnSubscriptionFailedEvent, OnSuccessfulArrivedEvent, OnNotification, OnSubscriptionEstablishedEvent {

  private final UiUpdateService uiUpdateService;
  private final EventOverviewService eventOverviewService;

  @Override
  public ResponseEntity<Void> onNotification(Notification message) {
    log.info("Received onNotification: {}", message);
    uiUpdateService.updateAllUi(() -> com.vaadin.flow.component.notification.Notification.show(message.getMessage()));
    uiUpdateService.updateAllUi(() -> eventOverviewService.increaseEventCounter(message));
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<Void> onAlert(Alert message) {
    log.info("Received onAlert: {}", message);
    uiUpdateService.updateAllUi(() -> com.vaadin.flow.component.notification.Notification.show(message.getReason(), 3000, Position.BOTTOM_END));
    uiUpdateService.updateAllUi(() -> eventOverviewService.increaseEventCounter(message));
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<Void> onSubscriptionEstablishedEvent(SubscriptionEstablishedEvent message) {
    log.info("Received onSubscriptionEstablishedEvent: {}", message);
    uiUpdateService.updateAllUi(
        () -> com.vaadin.flow.component.notification.Notification.
            show("New Subscription: " + message.getClientName(), 3000, Position.BOTTOM_END));
    uiUpdateService.updateAllUi(() -> eventOverviewService.addSubscription(message));
    uiUpdateService.updateAllUi(() -> eventOverviewService.increaseEventCounter(message));
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<Void> onSubscriptionFailedEvent(SubscriptionFailedEvent message) {
    log.info("Received onSubscriptionFailedEvent: {}", message);
    uiUpdateService.updateAllUi(
        () -> com.vaadin.flow.component.notification.Notification.show("Subscription Failed: " + message.getClientName(), 3000, Position.BOTTOM_END));
    uiUpdateService.updateAllUi(() -> eventOverviewService.increaseEventCounter(message));
    return ResponseEntity.ok().build();
  }

  public ResponseEntity<Void> onSuccessfulArriveEvent(SuccessfulArrivedEvent message) {
    log.info("Received onSuccessfulArriveEvent: {}", message);
    uiUpdateService.updateAllUi(
        () -> com.vaadin.flow.component.notification.Notification.show("Successful Arrived: " + message.getClientName(), 3000, Position.BOTTOM_END));
    uiUpdateService.updateAllUi(() -> eventOverviewService.onEventArrived(message));
    uiUpdateService.updateAllUi(() -> eventOverviewService.increaseEventCounter(message));
    return ResponseEntity.ok().build();
  }
}
