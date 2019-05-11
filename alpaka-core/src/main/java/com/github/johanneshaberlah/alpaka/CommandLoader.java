package com.github.johanneshaberlah.alpaka;

import com.github.johanneshaberlah.alpaka.filter.TypeFilter;
import com.github.johanneshaberlah.alpaka.filter.TypeFilters;
import com.github.johanneshaberlah.alpaka.filter.TypeSearcher;
import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public final class CommandLoader {

  private Package basePackage;

  private CommandLoader(Package basePackage) {
    this.basePackage = basePackage;
  }

  public void load(Collection<Command> target, Consumer<Command> processor) {
    try {
      TypeSearcher.create(ClassPath.from(this.getClass().getClassLoader()))
        .filter(
          this.basePackage.getName(),
          TypeFilters.annotatedWith(CommandInfo.class))
        .forEach(aClass -> {
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

  public static CommandLoader create(Package basePackage) {
    return new CommandLoader(basePackage);
  }
}
