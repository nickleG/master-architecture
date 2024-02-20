package de.ng.master.architecture.eventlib.pubsub.client;

import de.ng.master.architecture.eventlib.config.LibConfig;
import de.ng.master.architecture.eventlib.events.Event;
import de.ng.master.architecture.eventlib.events.SubscriptionEstablishedEvent;
import de.ng.master.architecture.eventlib.events.SubscriptionFailedEvent;
import de.ng.master.architecture.eventlib.events.SubscriptionRemoveFailEvent;
import de.ng.master.architecture.eventlib.events.SubscriptionRemovedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
@Slf4j
public class SubscribeClient {

  private final PublishClient publishClient;

  private final LibConfig config;

  private final RestTemplate restTemplate;
  private static final String SUBSCRIPTION_URL = "http://localhost:8083/subscribe";
  private static final String UNSUBSCRIPTION_URL = "http://localhost:8083/unsubscribe";

  public void subscribe(Class<? extends Event> topic) {
    Subscription subscription = new Subscription(config.getCallbackUrl(), topic.getSimpleName(), config.getClientName());
    log.info("Subscribing to {}", subscription);
    ResponseEntity<Void> voidResponseEntity = restTemplate.postForEntity(SUBSCRIPTION_URL, subscription, Void.class);
    if (voidResponseEntity.getStatusCode().is2xxSuccessful()) {
      SubscriptionEstablishedEvent subscriptionEstablishedEvent = SubscriptionEstablishedEvent.builder()
          .clientName(config.getClientName())
          .clientUrl(config.getCallbackUrl())
          .topic(topic.getSimpleName())
          .build();
      publishClient.publish(subscriptionEstablishedEvent);
    } else {
      SubscriptionFailedEvent failedEvent = SubscriptionFailedEvent.builder()
          .clientName(config.getClientName())
          .clientUrl(config.getCallbackUrl())
          .topic(topic.getSimpleName())
          .subscriptionUrl(config.getPublishUrl())
          .build();
      publishClient.publish(failedEvent);
    }
  }

  public void unSubscribe(Class<? extends Event> topic) {
    Subscription subscription = new Subscription(config.getCallbackUrl(), topic.getSimpleName(), config.getClientName());
    log.info("Subscribing to {}", subscription);
    ResponseEntity<Void> voidResponseEntity = restTemplate.postForEntity(UNSUBSCRIPTION_URL, subscription, Void.class);
    if (voidResponseEntity.getStatusCode().is2xxSuccessful()) {
      SubscriptionRemovedEvent subscriptionRemovedEvent = SubscriptionRemovedEvent.builder()
          .clientName(config.getClientName())
          .clientUrl(config.getCallbackUrl())
          .topic(topic.getSimpleName())
          .build();
      publishClient.publish(subscriptionRemovedEvent);
    } else {
      SubscriptionRemoveFailEvent failedEvent = SubscriptionRemoveFailEvent.builder()
          .clientName(config.getClientName())
          .clientUrl(config.getCallbackUrl())
          .topic(topic.getSimpleName())
          .subscriptionUrl(config.getPublishUrl())
          .build();
      publishClient.publish(failedEvent);
    }
  }
}
