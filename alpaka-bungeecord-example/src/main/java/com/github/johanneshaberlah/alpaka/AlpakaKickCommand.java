package com.github.johanneshaberlah.alpaka;

import com.github.johanneshaberlah.alpaka.Command;
import com.github.johanneshaberlah.alpaka.CommandContext;
import com.github.johanneshaberlah.alpaka.CommandInfo;
import com.github.johanneshaberlah.alpaka.exception.CommandException;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

@CommandInfo(usage = "/kick <name> <reason>", permission = "", subCommandPackage = "")
public class AlpakaKickCommand implements Command {

  @Override
  public void execute(CommandContext context) throws CommandException {
    ProxiedPlayer proxiedPlayer = context.arguments().require(ProxiedPlayer.class);
    String reason = context.arguments().next();
    proxiedPlayer.disconnect(TextComponent.fromLegacyText(reason));
    context.sink().write(context.remotePlayer(), "Der Spieler wurde erfolgreich gekickt!");
  }
}
