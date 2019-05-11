package com.github.johanneshaberlah.alpaka.filter;


import com.google.common.base.Preconditions;
import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class TypeSearcher {

  private final ClassPath classPath;

  private TypeSearcher(ClassPath classPath) {
    this.classPath = classPath;
  }


  /**
   * This Method is capable of capturing all loaded and unloaded classes provided by the given classPath in all packages recursively
   * but will load all of them. This may cause performance issues and should not be used with large classpaths.
   *
   * @param typeFilters given filters.
   * @return Stream of {@link com.google.common.reflect.ClassPath.ClassInfo} matching the given filters.
   * @throws NullPointerException if typeFilters is null
   */
  public Stream<? extends Class<?>> filter(TypeFilter... typeFilters) {
    return filter("", Preconditions.checkNotNull(typeFilters));
  }

  /**
   * This Method is capable of capturing all loaded and unloaded classes provided by the given classPath and subPackage
   * but will load all of them.
   *
   * @param packageName sub package to search.
   * @param typeFilters given filters.
   * @return Stream of {@link com.google.common.reflect.ClassPath.ClassInfo} matching the given filters.
   */
  public Stream<? extends Class<?>> filter(String packageName, TypeFilter... typeFilters) {
    return this.filter(new String[]{packageName}, typeFilters);
  }

  /**
   * This Method is capable of capturing all loaded and unloaded classes provided by the given classPath and subPackage
   * but will load all of them.
   *
   * @param packageNames sub packages to search.
   * @param typeFilters  given filters.
   * @return Stream of {@link com.google.common.reflect.ClassPath.ClassInfo} matching the given filters.
   */
  public Stream<? extends Class<?>> filter(String[] packageNames, TypeFilter... typeFilters) {
    Preconditions.checkNotNull(packageNames);
    Preconditions.checkNotNull(typeFilters);
    return (packageNames.length == 0 ?
      this.classPath.getAllClasses() :
      Arrays.stream(packageNames)
        .flatMap(packageName -> this.classPath.getTopLevelClassesRecursive(packageName)
          .stream()).collect(Collectors.toList()))
      .stream()
      .map(classInfo -> {
        try {
          return classInfo.load();
        } catch (Throwable ex) {
        }
        return null;
      })
      .filter(Objects::nonNull)
      .filter(clazz -> Arrays.stream(typeFilters)
        .allMatch(typeFilter -> typeFilter.matches(clazz))
      );
  }


  public static TypeSearcher create(Object context) throws IOException {
    return create(ClassPath.from(context.getClass().getClassLoader()));
  }

  /**
   * @param classPath given ClassPath to filter.
   * @return Constructs a {@link TypeSearcher} with argument check.
   * @throws NullPointerException if classLoader is null.
   */
  public static TypeSearcher create(ClassPath classPath) {
    return new TypeSearcher(Preconditions.checkNotNull(classPath));
  }


}
