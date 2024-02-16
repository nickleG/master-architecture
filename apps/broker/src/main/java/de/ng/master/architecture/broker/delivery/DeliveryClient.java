package de.ng.master.architecture.broker.delivery;

import de.ng.master.architecture.broker.publisher.SimpleEventEntity;
import de.ng.master.architecture.broker.subscriber.SubscriptionEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeliveryClient {

  private final RestTemplate restTemplate;


  public ResponseEntity<Void> deliver(SimpleEventEntity event, SubscriptionEntity subscriber) {
    log.info("Delivering message: {}", event);
    log.info("Sending event to subscriber: {}", subscriber);
    //post application json to the subscriber
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    // Hier event.getJsonContent() ist der JSON-Inhalt
    HttpEntity<String> request = new HttpEntity<>(event.getJsonContent(), headers);

    return restTemplate.postForEntity(subscriber.getCallbackUrl(), request, Void.class);

  }

}
