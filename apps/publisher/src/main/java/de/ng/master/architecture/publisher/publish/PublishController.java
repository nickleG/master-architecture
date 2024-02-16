package de.ng.master.architecture.publisher.publish;

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
}
