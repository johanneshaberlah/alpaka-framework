package com.github.johanneshaberlah.alpaka;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandInfo {

  String usage();

  String permission();

  String subCommandPackage();
}
