package de.ng.master.architecture.eventlib.events;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class SubscriptionEstablishedEvent extends Event {

  private String clientUrl;
  private String clientName;
  private String topic;

  public SubscriptionEstablishedEvent(String clientUrl, String clientName, String topic) {
    super();
    this.clientUrl = clientUrl;
    this.clientName = clientName;
    this.topic = topic;
  }

}
