package com.github.johaneshaberlah.alpaka;

import com.github.johanneshaberlah.alpaka.CommandContextFactory;
import com.github.johanneshaberlah.alpaka.CommandFactory;
import com.github.johanneshaberlah.alpaka.MessageSink;
import com.google.common.base.Preconditions;
import com.google.inject.AbstractModule;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;

public class InjectModule extends AbstractModule {
  private Plugin plugin;

  private InjectModule(Plugin plugin) {
    this.plugin = plugin;
  }

  @Override
  protected void configure() {
    Package basePackage = this.plugin.getClass().getPackage();

    this.bind(Plugin.class).toInstance(this.plugin);
    this.bind(Server.class).toInstance(this.plugin.getServer());
    this.bind(MessageSink.class).toInstance(SpigotMessageSink.of(this.plugin.getServer()));
    this.bind(CommandFactory.class).toInstance(CommandFactory.create(basePackage));
    this.bind(CommandContextFactory.class)
        .toInstance(
            CommandContextFactory.create(SpigotMessageSink.of(this.plugin.getServer()), basePackage));
  }

  public static InjectModule create(Plugin plugin) {
    Preconditions.checkNotNull(plugin);
    return new InjectModule(plugin);
  }
}
