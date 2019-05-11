package com.github.johanneshaberlah.alpaka;

import com.github.johanneshaberlah.alpaka.filter.TypeFilters;
import com.github.johanneshaberlah.alpaka.filter.TypeSearcher;
import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.util.Map;
import java.util.function.BiConsumer;

public class ArgumentTypeLoader {

  private Package basePackage;

  private ArgumentTypeLoader(Package basePackage) {
    this.basePackage = basePackage;
  }

  public void load(Map<Class, ArgumentType> target, BiConsumer<Class, ArgumentType> processor) {
    try {
      TypeSearcher.create(ClassPath.from(this.getClass().getClassLoader()))
          .filter(this.basePackage.getName(), TypeFilters.annotatedWith(RegisterArgumentType.class))
          .forEach(
              aClass -> {
                try {
                  Object instance = aClass.newInstance();
                  if (!(instance instanceof ArgumentType)) {
                    return;
                  }
                  RegisterArgumentType registerArgumentType = aClass.getAnnotation(RegisterArgumentType.class);
                  ArgumentType argumentType = (ArgumentType) instance;
                  target.put(registerArgumentType.argumentType(), argumentType);
                  processor.accept(registerArgumentType.argumentType(), argumentType);
                } catch (InstantiationException | IllegalAccessException e) {
                  e.printStackTrace();
                }
              });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static ArgumentTypeLoader create(Package basePackage) {
    return new ArgumentTypeLoader(basePackage);
  }
}
