package de.ng.master.architecture.subscriber.subscription;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class SubscriptionClient {

  private final RestTemplate restTemplate;

  private static final String SUBSCRIPTION_URL = "http://localhost:8083/subscribe";

  public void subscribe(Subscription subscription) {
    ResponseEntity<Void> responseEntity = restTemplate.postForEntity(SUBSCRIPTION_URL, subscription, Void.class);
    if (responseEntity.getStatusCode().is2xxSuccessful()) {
      log.info("Subscription successful: {}", subscription);
    } else {
      log.error("Subscription failed{}", subscription);
    }

  }
}
