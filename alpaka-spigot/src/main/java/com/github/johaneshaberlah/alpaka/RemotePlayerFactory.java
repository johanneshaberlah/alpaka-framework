package com.github.johaneshaberlah.alpaka;

import com.github.johanneshaberlah.alpaka.RemotePlayer;
import org.bukkit.entity.Player;

public class RemotePlayerFactory {

  public static RemotePlayer of(Player player) {
    return RemotePlayer.create(player.getUniqueId(), player.getName());
  }
}
