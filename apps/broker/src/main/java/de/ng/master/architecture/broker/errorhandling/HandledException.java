package de.ng.master.architecture.broker.errorhandling;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class HandledException extends Exception {

  private final ErrorResponse errorResponse;

}
