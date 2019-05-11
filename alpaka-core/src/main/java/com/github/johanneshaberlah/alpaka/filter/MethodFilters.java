package com.github.johanneshaberlah.alpaka.filter;

import java.lang.annotation.Annotation;

public class MethodFilters {

  public static MethodFilter annotatedWith(Annotation annotation) {
    return method -> method.isAnnotationPresent(annotation.getClass()) && Annotations.equals(annotation, method.getDeclaredAnnotation(annotation.getClass()));
  }

  public static MethodFilter annotatedWith(Class<? extends Annotation> annotation) {
    return method -> method.isAnnotationPresent(annotation);
  }

}
