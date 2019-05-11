package com.github.johanneshaberlah.alpaka.filter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@FunctionalInterface
public interface MethodFilter {

  boolean matches(Method method);


}
