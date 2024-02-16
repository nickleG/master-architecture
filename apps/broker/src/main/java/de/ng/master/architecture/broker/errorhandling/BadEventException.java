package de.ng.master.architecture.broker.errorhandling;

public class BadEventException extends HandledException {

  public BadEventException() {
    super(ErrorResponse.BAD_EVENT);
  }
}
