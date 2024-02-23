package de.ng.master.architecture.data;

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
