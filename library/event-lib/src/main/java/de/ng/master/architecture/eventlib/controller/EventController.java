package de.ng.master.architecture.eventlib.controller;

import de.ng.master.architecture.eventlib.data.TestData;
import de.ng.master.architecture.eventlib.events.Alert;
import de.ng.master.architecture.eventlib.events.Notification;
import de.ng.master.architecture.eventlib.pubsub.client.PublishClient;
import de.ng.master.architecture.eventlib.pubsub.client.SubscribeClient;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EventController {

  private final PublishClient publishClient;
  private final SubscribeClient subscribeClient;
  private boolean run;
  private long number = 0;
  private long timout = 5000;

  @GetMapping("/publish/{event}")
  public void publish(@PathVariable String event) {
    log.info("Publishing event");
    switch (event) {
      case "alert":
        publishClient.publish(Alert.builder().number(TestData.getNumber()).reason(TestData.generateMessage()).build());
        break;
      case "notification":
        publishClient.publish(Notification.builder().message(TestData.generateMessage()).number(TestData.getNumber()).type(TestData.generateMessage()).build());
        break;
      default:
        log.error("Unknown event type: {}", event);
    }
  }

  @GetMapping("/subscribe/{event}")
  public void subscribe(@PathVariable String event) {
    log.info("Subscribing to event");
    switch (event) {
      case "alert":
        subscribeClient.subscribe(Alert.class);
        break;
      case "notification":
        subscribeClient.subscribe(Notification.class);
        break;
      default:
        log.error("Unknown event type: {}", event);
    }
  }

  @GetMapping("/unsubscribe/{event}")
  public void unsubscribe(@PathVariable String event) {
    log.info("Unsubscribing from event");
    switch (event) {
      case "alert":
        subscribeClient.unSubscribe(Alert.class);
        break;
      case "notification":
        subscribeClient.unSubscribe(Notification.class);
        break;
      default:
        log.error("Unknown event type: {}", event);
    }
  }

  @GetMapping("/unsubscribe")
  public void unsubscribe() {
    log.info("Unsubscribing from every event");
    subscribeClient.unSubscribe(Alert.class);
    subscribeClient.unSubscribe(Notification.class);
  }


  @GetMapping("/start")
  public void start() {
    this.run = true;
    new Thread(this::doNotify).start();
  }

  @GetMapping("/timeout/{timeout}")
  public void start(long millis) {
    if (millis >= 2000) {
      this.timout = millis;
    }

    new Thread(this::doNotify).start();
  }

  @GetMapping("/stop")
  public void stop() {
    this.run = false;
  }

  public void doNotify() {
    while (run) {
      double random = Math.random();
      if (random < 0.9) {
        Notification notification = Notification.builder()
            .type("running")
            .number(number++)
            .message(TestData.generateMessage())
            .build();
        log.info("Pushing notification: {} ", notification);
        publishClient.publish(notification);
      } else {
        Alert alert = Alert.builder()
            .number(number++)
            .reason(TestData.generateMessage())
            .build();
        log.info("Pushing alert: {} ", alert);
        publishClient.publish(alert);
      }

      try {
        double v = timout + 2000 * new Random().nextGaussian();
        Thread.sleep((long) v);
      } catch (InterruptedException e) {
        log.info(e.getMessage());
      }
    }
  }

}
