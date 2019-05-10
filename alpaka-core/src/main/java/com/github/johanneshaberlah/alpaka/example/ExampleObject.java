package com.github.johanneshaberlah.alpaka.example;

public class ExampleObject {

  private String text;

  private ExampleObject(String text) {
    this.text = text;
  }

  public String getText() {
    return String.valueOf(text);
  }

  public static ExampleObject create(String text) {
    return new ExampleObject(text);
  }
}
