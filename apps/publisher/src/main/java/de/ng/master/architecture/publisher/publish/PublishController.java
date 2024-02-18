package de.ng.master.architecture.publisher.publish;

import de.ng.master.architecture.eventlib.events.Notification;
import de.ng.master.architecture.eventlib.pubsub.client.PublishClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PublishController {

  private final PublishClient client;
  private long number = 0;
  private boolean run;

  @GetMapping("/push")
  public void push() {
    Notification notification = Notification.builder()
        .type("alert")
        .number(number++)
        .message(TestData.generateMessage())
        .build();
    log.info("Pushing notification: {} ", notification);
    client.publish(notification);
  }

  @GetMapping("/start")
  public void start() {
    this.run = true;

    new Thread(this::doNotify).start();
  }

  @GetMapping("/stop")
  public void stop() {
    this.run = false;
  }

  public void doNotify() {
    while (run) {
      Notification notification = Notification.builder()
          .type("alert")
          .number(number++)
          .message(TestData.generateMessage())
          .build();
      log.info("Pushing notification: {} ", notification);
      client.publish(notification);
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        log.info(e.getMessage());
      }
    }
  }
}
