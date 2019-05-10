package com.github.johanneshaberlah.alpaka.example;

import com.github.johanneshaberlah.alpaka.ArgumentType;

public class ExampleObjectArgumentType implements ArgumentType<ExampleObject> {

  private ExampleObjectArgumentType(){}

  @Override
  public ExampleObject transform(String argument) {
    return ExampleObject.create(argument);
  }

  public static ExampleObjectArgumentType create(){
    return new ExampleObjectArgumentType();
  }
}
