package de.ng.master.architecture.publisher.publish;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Notification implements Serializable {

  private String type;
  private Long number;
  private String message;


}
