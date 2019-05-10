package com.github.johanneshaberlah.alpaka.exception;

import com.google.common.base.Preconditions;

public class ArgumentTypeAdapterNotPresentException extends CommandException {

  private Class type;

  private ArgumentTypeAdapterNotPresentException(Class type) {
    this.type = type;
  }

  @Override
  public String getMessage() {
    return "There is no adapter registered for class " + this.type.getSimpleName();
  }

  public static ArgumentTypeAdapterNotPresentException create(Class type) {
    Preconditions.checkNotNull(type);
    return new ArgumentTypeAdapterNotPresentException(type);
  }
}
