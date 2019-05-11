package com.github.johaneshaberlah.alpaka;

import com.github.johanneshaberlah.alpaka.PermissionAdapter;
import com.github.johanneshaberlah.alpaka.RemotePlayer;
import com.google.inject.Inject;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class SpigotPermissionAdapter implements PermissionAdapter {

  private Server server;

  @Inject
  private SpigotPermissionAdapter(Server server) {
    this.server = server;
  }

  @Override
  public boolean hasPermission(RemotePlayer remotePlayer, String permission) {
    Player player = this.server.getPlayer(remotePlayer.getUniqueId());
    return player.hasPermission(permission);
  }
}
