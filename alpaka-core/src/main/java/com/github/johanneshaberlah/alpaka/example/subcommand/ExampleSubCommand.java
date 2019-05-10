package com.github.johanneshaberlah.alpaka.example.subcommand;

import com.github.johanneshaberlah.alpaka.CommandContext;
import com.github.johanneshaberlah.alpaka.SubCommand;
import com.github.johanneshaberlah.alpaka.SubCommandInfo;
import com.github.johanneshaberlah.alpaka.example.ExampleCommand;
import com.github.johanneshaberlah.alpaka.example.ExampleObject;
import com.github.johanneshaberlah.alpaka.exception.CommandException;

@SubCommandInfo(parent = ExampleCommand.class, usage = "example print <message>", permission = "")
public class ExampleSubCommand implements SubCommand {

  @Override
  public void execute(CommandContext context) throws CommandException {
    context.arguments().skip();
    ExampleObject exampleObject = context.arguments().require(ExampleObject.class);
    System.out.println("ExampleObject Content is: " + exampleObject.getText());
  }
}
