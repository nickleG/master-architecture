package de.ng.master.architecture.subscriber.subscription;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Subscription implements Serializable {

  private String callbackUrl;
  private String topic;

}
