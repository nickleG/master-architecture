package de.ng.master.architecture.broker.subscriber;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Subscriber {

  private String callbackUrl;
  private String topic;

}
