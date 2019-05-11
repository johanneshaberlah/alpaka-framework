package com.github.johanneshaberlah.alpaka;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class ProxyMessageSink implements MessageSink {

  private ProxyServer proxyServer;

  private ProxyMessageSink(ProxyServer proxyServer) {
    this.proxyServer = proxyServer;
  }

  @Override
  public void write(RemotePlayer remotePlayer, String message) {
    ProxiedPlayer proxiedPlayer = this.proxyServer.getPlayer(remotePlayer.getUniqueId());
    proxiedPlayer.sendMessage(TextComponent.fromLegacyText(message));
  }

  public static ProxyMessageSink of(ProxyServer proxyServer) {
    Preconditions.checkNotNull(proxyServer);
    return new ProxyMessageSink(proxyServer);
  }
}
