package com.github.johanneshaberlah.alpaka;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class RemotePlayerFactory {

  public static RemotePlayer of(ProxiedPlayer proxiedPlayer) {
    return RemotePlayer.create(proxiedPlayer.getUniqueId(), proxiedPlayer.getName());
  }
}
