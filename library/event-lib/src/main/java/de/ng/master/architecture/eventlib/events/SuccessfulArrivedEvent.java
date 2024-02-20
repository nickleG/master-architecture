package de.ng.master.architecture.eventlib.events;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SuccessfulArrivedEvent extends Event {

  private UUID arrivedEventId;
  private String clientName;
  private String topic;

  public SuccessfulArrivedEvent(UUID event, String topic, String clientName) {
    super();
    this.arrivedEventId = event;
    this.topic = topic;
    this.clientName = clientName;
  }
}
