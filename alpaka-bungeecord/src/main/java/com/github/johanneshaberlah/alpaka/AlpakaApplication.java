package com.github.johanneshaberlah.alpaka;

import com.github.johanneshaberlah.alpaka.proxy.ChatListener;
import com.google.inject.Guice;
import com.google.inject.Injector;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class AlpakaApplication {

  private Injector injector;

  private AlpakaApplication() {}

  public void run(Plugin plugin) {
    injector = Guice.createInjector(InjectModule.create(plugin));

    PluginManager pluginManager = plugin.getProxy().getPluginManager();
    pluginManager.registerListener(plugin, injector.getInstance(ChatListener.class));
  }

  public void registerAdapter(Class type, ArgumentType argumentType) {
    this.injector.getInstance(CommandContextFactory.class).registerAdapter(type, argumentType);
  }

  public static AlpakaApplication create() {
    return new AlpakaApplication();
  }
}
