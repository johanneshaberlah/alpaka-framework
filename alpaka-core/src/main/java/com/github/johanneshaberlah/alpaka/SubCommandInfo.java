package com.github.johanneshaberlah.alpaka;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface SubCommandInfo {

  Class parent();

  String usage();

  String permission();

  boolean ignoreLength() default false;
}
