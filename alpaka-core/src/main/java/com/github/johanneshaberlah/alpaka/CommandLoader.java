package com.github.johanneshaberlah.alpaka;

import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public final class CommandLoader {

  private CommandLoader(){}

  public void load(Collection<Command> target, Consumer<Command> processor) {
    try {
      ClassPath.from(this.getClass().getClassLoader()).getAllClasses().stream()
          .map(ClassPath.ClassInfo::load)
          .collect(Collectors.toList())
          .stream()
          .filter(aClass -> aClass.isAnnotationPresent(CommandInfo.class))
          .forEach(
              aClass -> {
                try {
                  Object instance = aClass.newInstance();
                  if (!(instance instanceof Command)) {
                    return;
                  }
                  Command command = (Command) instance;
                  target.add(command);
                  processor.accept(command);
                } catch (InstantiationException | IllegalAccessException e) {
                  e.printStackTrace();
                }
              });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static CommandLoader create(){
    return new CommandLoader();
  }
}
