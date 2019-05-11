package com.github.johanneshaberlah.alpaka;

import com.github.johanneshaberlah.alpaka.AlpakaApplication;
import net.md_5.bungee.api.plugin.Plugin;

public class AlpakaProxyExamplePlugin extends Plugin {

  @Override
  public void onEnable() {
    AlpakaApplication alpakaApplication = AlpakaApplication.create();
    alpakaApplication.run(this);
  }
}
