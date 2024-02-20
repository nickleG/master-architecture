package de.ng.master.architecture.eventlib.pubsub.client;

import de.ng.master.architecture.eventlib.events.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
@Slf4j
public class PublishClient {

  private final RestTemplate restTemplate;

  public void publish(Event event) {

    String url = "http://localhost:8083/publish/" + event.getClass().getSimpleName();
    HttpEntity<Event> eventHttpEntity = new HttpEntity<>(event);
    restTemplate.exchange(url, HttpMethod.POST, eventHttpEntity, Void.class);
  }
}
