package com.github.johanneshaberlah.alpaka;

@FunctionalInterface
public interface ArgumentType<T> {

  T transform(String argument);
}
