package de.ng.master.architecture.eventlib.events;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Alert extends Event implements Serializable {

  private Long number;
  private String reason;

  public Alert(Long number, String reason) {
    super();
    this.number = number;
    this.reason = reason;
  }

}
