package de.ng.master.architecture.publisher.publish;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class PublishClient {

  private final RestTemplate restTemplate;

  public void publish(Notification notification) {
    restTemplate.postForEntity("http://localhost:8083/publish/" + Notification.class.getSimpleName(), notification, Void.class);
  }
}
