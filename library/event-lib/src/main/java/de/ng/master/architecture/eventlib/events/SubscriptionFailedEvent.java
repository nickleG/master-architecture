package de.ng.master.architecture.eventlib.events;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class SubscriptionFailedEvent extends Event {


  private String clientUrl;
  private String clientName;
  private String topic;
  private String subscriptionUrl;

  public SubscriptionFailedEvent(String clientUrl, String clientName, String topic, String subscriptionUrl) {
    super();
    this.clientUrl = clientUrl;
    this.clientName = clientName;
    this.topic = topic;
    this.subscriptionUrl = subscriptionUrl;
  }
}
