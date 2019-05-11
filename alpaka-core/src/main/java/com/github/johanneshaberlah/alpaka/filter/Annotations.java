package com.github.johanneshaberlah.alpaka.filter;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Annotations {

  public static <T extends Annotation> boolean equals(T t, T v) {
    if (t == null || v == null) return t == v;
    return Arrays.stream(t.annotationType().getDeclaredMethods())
      .allMatch(method -> {
        try {
          return method.invoke(t).equals(method.invoke(v));
        } catch (IllegalAccessException | InvocationTargetException e) {
          e.printStackTrace();
        }
        return false;
      });
  }

}
