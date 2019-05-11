package com.github.johanneshaberlah.alpaka;

import com.github.johanneshaberlah.alpaka.ArgumentType;
import com.github.johanneshaberlah.alpaka.RegisterArgumentType;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

@RegisterArgumentType(argumentType = ProxiedPlayer.class)
public class ProxiedPlayerArgumentType implements ArgumentType<ProxiedPlayer> {

  public ProxiedPlayerArgumentType(){}

  @Override
  public ProxiedPlayer transform(String s) {
    return ProxyServer.getInstance().getPlayer(s);
  }

}
