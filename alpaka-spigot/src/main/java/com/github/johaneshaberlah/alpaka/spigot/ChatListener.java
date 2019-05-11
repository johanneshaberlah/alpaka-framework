package com.github.johaneshaberlah.alpaka.spigot;

import com.github.johaneshaberlah.alpaka.RemotePlayerFactory;
import com.github.johanneshaberlah.alpaka.*;
import com.github.johanneshaberlah.alpaka.exception.CommandException;
import com.google.inject.Inject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

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
  public void onChat(AsyncPlayerChatEvent event) {
    Player player = event.getPlayer();
    if (!event.getMessage().startsWith("/")) {
      return;
    }
    String message = event.getMessage().substring(1);
    CommandContext commandContext =
        this.commandContextFactory.create(
            RemotePlayerFactory.of(player), message.split(" "));
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
