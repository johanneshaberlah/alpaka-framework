package com.github.johanneshaberlah.alpaka;

/**
 * MessageSink, which allows to send messages application independent. Since all target systems are
 * able to send a message as a string, i don't need to supply a char array or anything.
 */
public interface MessageSink {

  /**
   * Sends a message to a user.
   *
   * @param remotePlayer The user to whom the message should be sent.
   * @param message The message to be sent.
   */
  void write(RemotePlayer remotePlayer, String message);
}
