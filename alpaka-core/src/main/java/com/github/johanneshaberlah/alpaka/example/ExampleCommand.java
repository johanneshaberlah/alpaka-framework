package com.github.johanneshaberlah.alpaka.example;

import com.github.johanneshaberlah.alpaka.Command;
import com.github.johanneshaberlah.alpaka.CommandContext;
import com.github.johanneshaberlah.alpaka.CommandInfo;

@CommandInfo(
    usage = "example <message>",
    permission = "example.permission",
    subCommandPackage = "com.github.johanneshaberlah.alpaka.example.subcommand")
public class ExampleCommand implements Command {

  @Override
  public void execute(CommandContext context) {
    ExampleObject exampleObject = context.arguments().require(ExampleObject.class);
    if (exampleObject != null) {
      System.out.println("Loaded ExampleObject with content " + exampleObject.getText());
    }
  }
}
