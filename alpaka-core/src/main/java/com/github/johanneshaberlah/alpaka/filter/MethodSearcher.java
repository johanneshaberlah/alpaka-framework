package com.github.johanneshaberlah.alpaka.filter;


import com.google.common.base.Preconditions;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

public class MethodSearcher {

  private final Class<?>[] classes;

  private MethodSearcher(Class<?>[] classes) {
    this.classes = classes;
  }

  public Stream<Method> filter(MethodFilter... methodFilters){
    return Arrays.stream(this.classes)
      .flatMap(clazz -> Arrays.stream(clazz.getDeclaredMethods()))
      .filter(method -> Arrays.stream(methodFilters)
        .allMatch(methodFilter -> methodFilter.matches(method)));
  }

  public static MethodSearcher create(Class<?>... classes){
    Preconditions.checkNotNull(classes);
    return new MethodSearcher(classes);
  }

}
