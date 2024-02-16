package de.ng.master.architecture.broker.errorhandling;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ErrorResponse implements Serializable {

  public static final ErrorResponse BAD_EVENT = new ErrorResponse("0001",
      "bad event published", HttpStatus.BAD_REQUEST);
  public static final ErrorResponse INTERNAL_ERROR = new ErrorResponse("INTERNAL-ERROR",
      "An Internal error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
  private final String errorCode;
  private final String errorMessage;
  private final HttpStatus httpStatusCode;
}
