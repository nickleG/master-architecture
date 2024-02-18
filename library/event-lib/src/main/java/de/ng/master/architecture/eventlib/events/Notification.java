package de.ng.master.architecture.eventlib.events;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Notification extends Event implements Serializable {

  private String type;
  private Long number;
  private String message;

  public Notification(String type, Long number, String message) {
    super();
    this.type = type;
    this.number = number;
    this.message = message;
  }
}

