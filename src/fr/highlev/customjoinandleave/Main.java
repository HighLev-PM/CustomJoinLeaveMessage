package fr.highlev.customjoinandleave;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import java.io.File;
import cn.nukkit.utils.ConfigSection;

import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerQuitEvent;

public class Main extends PluginBase implements Listener {
	
	Config config;
	
   public void onEnable() { 
	 this.getLogger().info("Activated!");
	 this.getServer().getPluginManager().registerEvents(this, this);
	 this.saveResource("config.yml");
	 this.config = new Config(this.getDataFolder() + "/config.yml", Config.YAML, new ConfigSection() {
			{
				put("join-message", "{PLAYER} joined the server!");
				put("leave-message", "{PLAYER} left the server!");
			}
      });
  }
   
   public void onDisable() {
	   this.getLogger().info("Desactivate!");
   }
   
   @EventHandler(priority = EventPriority.NORMAL)
   public void onJoin(PlayerJoinEvent ev){
	   String message = String.valueOf(this.config.get("join-message"));
       message = message.replace("{PLAYER}", ev.getPlayer().getName());
       ev.setJoinMessage(message);
   }
   
   @EventHandler(priority = EventPriority.NORMAL)
   public void onQuit(PlayerQuitEvent ev) {
	   String message = String.valueOf(this.config.get("leave-message"));
       message = message.replace("{PLAYER}", ev.getPlayer().getName());
       ev.setQuitMessage(message);
   }
}