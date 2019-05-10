package com.github.johanneshaberlah.alpaka;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Optional;

public final class CommandFactory {

  private final Collection<Command> commands = Lists.newArrayList();
  private CommandMatcher commandMatcher;

  private CommandFactory() {
    this.commandMatcher = CommandMatcher.create();
    this.loadCommands();
  }

  private void loadCommands() {
    CommandLoader commandLoader = CommandLoader.create();
    commandLoader.load(this.commands, Command::loadSubCommands);
  }

  public Optional<Command> create(CommandContext context) {
    for (Command command : this.commands) {
      if (this.commandMatcher.matches(command, context)){
        return Optional.of(command);
      }
      for (SubCommand subCommand : command.subCommands()) {
        if (this.commandMatcher.matches(subCommand, context)){
          return Optional.of(subCommand);
        }
      }
    }
    return Optional.empty();
  }


  public static CommandFactory create() {
    return new CommandFactory();
  }
}
