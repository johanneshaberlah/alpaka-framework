package com.github.johaneshaberlah.alpaka;

import com.github.johaneshaberlah.alpaka.spigot.ChatListener;
import com.github.johanneshaberlah.alpaka.ArgumentType;
import com.github.johanneshaberlah.alpaka.CommandContextFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class AlpakaApplication {

  private Injector injector;

  private AlpakaApplication() {}

  public void run(Plugin plugin) {
    injector = Guice.createInjector(InjectModule.create(plugin));

    PluginManager pluginManager = plugin.getServer().getPluginManager();
    pluginManager.registerEvents(injector.getInstance(ChatListener.class), plugin);
  }

  public void registerAdapter(Class type, ArgumentType argumentType) {
    this.injector.getInstance(CommandContextFactory.class).registerAdapter(type, argumentType);
  }

  public static AlpakaApplication create() {
    return new AlpakaApplication();
  }
}
