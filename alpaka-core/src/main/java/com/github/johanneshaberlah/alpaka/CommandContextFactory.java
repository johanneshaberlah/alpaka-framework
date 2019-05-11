package com.github.johanneshaberlah.alpaka;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

public final class CommandContextFactory {

  private Map<Class, ArgumentType> argumentTypes;
  private MessageSink messageSink;
  private Package basePackage;

  private CommandContextFactory(MessageSink messageSink, Package basePackage) {
    this.argumentTypes = Maps.newHashMap();
    this.messageSink = messageSink;
    this.basePackage = basePackage;
    this.loadArgumentTypes();
  }

  private void loadArgumentTypes() {
    System.out.println("[Alpaka] Loading ArgumentTypes...");
    ArgumentTypeLoader argumentTypeLoader = ArgumentTypeLoader.create(this.basePackage);
    argumentTypeLoader.load(
        argumentTypes,
        (aClass, argumentType) ->
            System.out.println(
                "[Alpaka] Registered TypeAdapter for class " + aClass.getSimpleName() + "."));
    System.out.println("[Alpaka] Loaded " + argumentTypes.size() + " ArgumentTypes.");
  }

  public CommandContext create(RemotePlayer remotePlayer, String[] raw) {
    CommandContext commandContext = CommandContext.create(remotePlayer, this.messageSink);
    commandContext.arguments = Arguments.create(raw, this.argumentTypes, commandContext);
    return commandContext;
  }

  public void registerAdapter(Class type, ArgumentType argumentAdapter) {
    if (!(this.argumentTypes.containsKey(type))) {
      this.argumentTypes.put(type, argumentAdapter);
    }
  }

  public Map<Class, ArgumentType> getArgumentAdapters() {
    return Collections.unmodifiableMap(argumentTypes);
  }

  public static CommandContextFactory create(MessageSink messageSink, Package basePackage) {
    Preconditions.checkNotNull(messageSink);
    Preconditions.checkNotNull(basePackage);
    return new CommandContextFactory(messageSink, basePackage);
  }
}
