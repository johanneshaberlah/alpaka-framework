package com.github.johanneshaberlah.alpaka;

import com.github.johanneshaberlah.alpaka.exception.ArgumentTypeAdapterNotPresentException;
import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public final class Arguments {
  private ElementIterator<String> incrementingArray;
  private Map<Class, ArgumentType> argumentTypes;
  private CommandContext commandContext;
  private String[] raw;

  private Arguments(String[] raw, Map<Class, ArgumentType> argumentTypes, CommandContext context) {
    this.raw = raw;
    this.incrementingArray = ElementIterator.create(raw, 0);
    this.argumentTypes = argumentTypes;
    this.commandContext = context;
  }

  public <E> E require(Class<E> type) {
    Optional<ArgumentType> optionalArgumentType = this.findByType(type);
    if (!(optionalArgumentType.isPresent())) {
      this.commandContext.fail(ArgumentTypeAdapterNotPresentException.create(type));
      return null;
    }
    Object parsed = optionalArgumentType.get().transform(this.incrementingArray.next());
    return (E) parsed;
  }

  public void skip() {
    incrementingArray.next();
  }

  public String[] raw() {
    return Arrays.copyOf(this.raw, this.raw.length);
  }

  private Optional<ArgumentType> findByType(Class type) {
    return Optional.ofNullable(this.argumentTypes.get(type));
  }

  public static Arguments create(
      String[] raw, Map<Class, ArgumentType> argumentTypes, CommandContext context) {
    Preconditions.checkNotNull(raw);
    Preconditions.checkNotNull(argumentTypes);
    Preconditions.checkNotNull(context);
    return new Arguments(raw, argumentTypes, context);
  }
}
