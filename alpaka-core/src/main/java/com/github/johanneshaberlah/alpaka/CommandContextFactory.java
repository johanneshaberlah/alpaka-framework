package com.github.johanneshaberlah.alpaka;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

public final class CommandContextFactory {

  private Map<Class, ArgumentType> argumentTypes;
  private MessageSink messageSink;

  private CommandContextFactory(MessageSink messageSink) {
    this.argumentTypes = Maps.newHashMap();
    this.messageSink = messageSink;
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

  public static CommandContextFactory create(MessageSink messageSink) {
    Preconditions.checkNotNull(messageSink);
    return new CommandContextFactory(messageSink);
  }
}
