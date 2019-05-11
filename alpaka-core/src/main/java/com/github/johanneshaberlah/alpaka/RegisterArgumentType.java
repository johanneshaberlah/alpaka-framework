package com.github.johanneshaberlah.alpaka;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RegisterArgumentType {

  Class argumentType();
}
