package com.github.johanneshaberlah.alpaka;

import com.google.common.base.Preconditions;
import com.google.inject.AbstractModule;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class InjectModule extends AbstractModule {
  private Plugin plugin;

  private InjectModule(Plugin plugin) {
    this.plugin = plugin;
  }

  @Override
  protected void configure() {
    Package basePackage = this.plugin.getClass().getPackage();

    this.bind(Plugin.class).toInstance(this.plugin);
    this.bind(ProxyServer.class).toInstance(this.plugin.getProxy());
    this.bind(MessageSink.class).toInstance(ProxyMessageSink.of(this.plugin.getProxy()));
    this.bind(CommandFactory.class).toInstance(CommandFactory.create(basePackage));
    this.bind(CommandContextFactory.class)
        .toInstance(
            CommandContextFactory.create(ProxyMessageSink.of(this.plugin.getProxy()), basePackage));
  }

  public static InjectModule create(Plugin plugin) {
    Preconditions.checkNotNull(plugin);
    return new InjectModule(plugin);
  }
}
