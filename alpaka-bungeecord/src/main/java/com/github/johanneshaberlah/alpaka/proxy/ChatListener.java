package com.github.johanneshaberlah.alpaka.proxy;

import com.github.johanneshaberlah.alpaka.*;
import com.github.johanneshaberlah.alpaka.exception.CommandException;
import com.google.inject.Inject;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Optional;

public class ChatListener implements Listener {

  private CommandFactory commandFactory;
  private CommandContextFactory commandContextFactory;

  @Inject
  private ChatListener(
      CommandFactory commandFactory,
      CommandContextFactory commandContextFactory) {
    this.commandFactory = commandFactory;
    this.commandContextFactory = commandContextFactory;
  }

  @EventHandler
  public void onChat(ChatEvent event) {
    ProxiedPlayer proxiedPlayer = (ProxiedPlayer) event.getSender();
    if (!event.getMessage().startsWith("/")) {
      return;
    }
    String message = event.getMessage().substring(1);
    CommandContext commandContext =
        this.commandContextFactory.create(
            RemotePlayerFactory.of(proxiedPlayer), message.split(" "));
    Optional<Command> optionalCommand = this.commandFactory.create(commandContext);
    if (!optionalCommand.isPresent()) {
      return;
    }
    Command command = optionalCommand.get();
    try {
      command.execute(commandContext);
      event.setCancelled(true);
    } catch (CommandException e) {
      e.printStackTrace();
    }
  }
}
