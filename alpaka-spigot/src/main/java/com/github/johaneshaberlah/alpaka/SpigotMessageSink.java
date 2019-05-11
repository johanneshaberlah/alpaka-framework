package com.github.johaneshaberlah.alpaka;

import com.github.johanneshaberlah.alpaka.MessageSink;
import com.github.johanneshaberlah.alpaka.RemotePlayer;
import com.google.common.base.Preconditions;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class SpigotMessageSink implements MessageSink {

  private Server server;

  private SpigotMessageSink(Server server) {
    this.server = server;
  }

  @Override
  public void write(RemotePlayer remotePlayer, String message) {
    Player player = this.server.getPlayer(remotePlayer.getUniqueId());
    player.sendMessage(message);
  }

  public static SpigotMessageSink of(Server server) {
    Preconditions.checkNotNull(server);
    return new SpigotMessageSink(server);
  }
}
