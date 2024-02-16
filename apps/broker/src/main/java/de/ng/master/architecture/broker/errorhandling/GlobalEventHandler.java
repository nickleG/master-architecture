package de.ng.master.architecture.broker.errorhandling;

import static de.ng.master.architecture.broker.errorhandling.ErrorResponse.INTERNAL_ERROR;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalEventHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler
  public ResponseEntity<String> handleRuntimeException(final RuntimeException exception) {
    log.error("Internal runtime exception occurred: ", exception);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(INTERNAL_ERROR.getErrorMessage());
  }

  @ExceptionHandler
  public ResponseEntity<String> handleAccessDeniedException(final HandledException handledException) {
    log.info("HandledException occurred: " + handledException.getErrorResponse(), handledException);
    return ResponseEntity.status(handledException.getErrorResponse().getHttpStatusCode()).body(handledException.getErrorResponse().getErrorCode());
  }

}
