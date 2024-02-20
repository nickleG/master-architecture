package de.ng.master.architecture.views.eventoverview;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionState {

  private final String clientName;
  private final String topic;
  private int counter;

  public SubscriptionState(String clientName, String topic) {
    this.clientName = clientName;
    this.topic = topic;
    this.counter = 0;
  }


}
