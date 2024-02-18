package de.ng.master.architecture.eventlib.pubsub.client;

import de.ng.master.architecture.eventlib.config.LibConfig;
import de.ng.master.architecture.eventlib.events.Event;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Component
public class PublishClient {

  private final RestTemplate restTemplate;
  LibConfig config;

  public void publish(Event event) {
    restTemplate.postForEntity("http://localhost:8083/publish/" + event.getClass().getSimpleName(), event, Void.class);
  }
}
