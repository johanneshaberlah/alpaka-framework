package com.github.johanneshaberlah.alpaka;

import com.github.johanneshaberlah.alpaka.exception.CommandException;
import com.google.common.collect.Lists;
import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

public interface Command {

  Collection<SubCommand> subCommands = Lists.newLinkedList();

  void execute(CommandContext context) throws CommandException;

  default boolean isSubCommand() {
    return this.getClass().isAnnotationPresent(SubCommandInfo.class);
  }

  default Collection<SubCommand> subCommands() {
    return this.subCommands;
  }

  default void loadSubCommands() {
    CommandInfo commandInfo = this.getClass().getAnnotation(CommandInfo.class);
    if (commandInfo == null) {
      System.out.println("SubCommands must be annotated with SubCommand annotation!");
      return;
    }
    try {
      ClassPath classPath = ClassPath.from(this.getClass().getClassLoader());
      Collection<Class> classes =
          classPath.getTopLevelClasses(commandInfo.subCommandPackage()).stream()
              .map(ClassPath.ClassInfo::load)
              .collect(Collectors.toList());
      for (Class aClass : classes) {
        subCommands.add((SubCommand) aClass.newInstance());
      }
    } catch (IOException | IllegalAccessException | InstantiationException e) {
      e.printStackTrace();
    }
  }
}
