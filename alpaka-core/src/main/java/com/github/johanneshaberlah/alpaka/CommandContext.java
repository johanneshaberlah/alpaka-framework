package com.github.johanneshaberlah.alpaka;

import com.github.johanneshaberlah.alpaka.exception.CommandException;
import com.google.common.base.Preconditions;

public class CommandContext {

  private RemotePlayer remotePlayer;
  Arguments arguments;
  private MessageSink messageSink;

  private CommandContext(RemotePlayer remotePlayer, MessageSink messageSink) {
    this.remotePlayer = remotePlayer;
    this.messageSink = messageSink;
  }

  public void fail(CommandException exception) {
    exception.printStackTrace();
  }

  public RemotePlayer remotePlayer() {
    return this.remotePlayer;
  }

  public Arguments arguments() {
    return this.arguments;
  }

  public MessageSink sink() {
    return this.messageSink;
  }

  public static CommandContext create(RemotePlayer remotePlayer, MessageSink messageSink) {
    Preconditions.checkNotNull(remotePlayer);
    Preconditions.checkNotNull(messageSink);
    return new CommandContext(remotePlayer, messageSink);
  }
}
