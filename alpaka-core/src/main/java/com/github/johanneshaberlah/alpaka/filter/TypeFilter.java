package com.github.johanneshaberlah.alpaka.filter;

@FunctionalInterface
public interface TypeFilter {

  boolean matches(Class clazz);

}
