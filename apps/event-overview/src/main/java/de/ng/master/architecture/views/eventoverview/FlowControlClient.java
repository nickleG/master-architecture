package de.ng.master.architecture.views.eventoverview;

import de.ng.master.architecture.eventlib.pubsub.client.Subscription;
import java.net.MalformedURLException;
import java.net.URL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
@Slf4j
public class FlowControlClient {

  private final RestTemplate restTemplate;

  public void start(Subscription subscription) {
    restTemplate.getForEntity(getUrl(subscription.getCallbackUrl()) + "/start", Void.class);
  }

  public void stop(Subscription subscription) {
    restTemplate.getForEntity(getUrl(subscription.getCallbackUrl()) + "/stop", Void.class);
  }

  public void timeout(Subscription subscription, String timeoutMillis) {
    restTemplate.getForEntity(getUrl(subscription.getCallbackUrl()) + "/timeout/" + timeoutMillis, Void.class);
  }

  public void unsubscribe(Subscription subscription, String event) {
    restTemplate.getForEntity(getUrl(subscription.getCallbackUrl()) + "/unsubscribe/" + event, Void.class);
  }

  public void subscribe(Subscription subscription, String event) {
    restTemplate.getForEntity(getUrl(subscription.getCallbackUrl()) + "/subscribe/" + event, Void.class);
  }

  public void publish(Subscription subscription, String event) {
    restTemplate.getForEntity(getUrl(subscription.getCallbackUrl()) + "/publish/" + event, Void.class);
  }

  private String getUrl(String url) {
    try {
      URL rl = new URL(url);
      return String.join("", rl.getProtocol(), "://", rl.getHost(), ":", String.valueOf(rl.getPort()));
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }

  }

}
