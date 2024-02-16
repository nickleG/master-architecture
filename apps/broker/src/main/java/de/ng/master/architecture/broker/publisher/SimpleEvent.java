package de.ng.master.architecture.broker.publisher;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SimpleEvent implements Serializable {

  private String topic;
  private String jsonContent;

}
