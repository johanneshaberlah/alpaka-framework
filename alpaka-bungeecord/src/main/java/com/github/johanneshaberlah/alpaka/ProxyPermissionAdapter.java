package com.github.johanneshaberlah.alpaka;

import com.google.inject.Inject;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class ProxyPermissionAdapter implements PermissionAdapter {

  private ProxyServer proxyServer;

  @Inject
  private ProxyPermissionAdapter(ProxyServer proxyServer) {
    this.proxyServer = proxyServer;
  }

  @Override
  public boolean hasPermission(RemotePlayer remotePlayer, String permission) {
    ProxiedPlayer proxiedPlayer = this.proxyServer.getPlayer(remotePlayer.getUniqueId());
    return proxiedPlayer.hasPermission(permission);
  }
}
