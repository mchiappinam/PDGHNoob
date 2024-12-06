package me.mchiappinam.pdghnoob;

import java.io.File;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;

public class Main extends JavaPlugin implements Listener {
	public boolean jaDivulgou = false;
	
  public void onEnable() {
    getServer().getPluginCommand("noob").setExecutor(new Comando(this));
    getServer().getPluginCommand("setnoob").setExecutor(new Comando(this));
    getServer().getPluginManager().registerEvents(this, this);

	File file = new File(getDataFolder(),"config.yml");
	if(!file.exists()) {
		try {
			saveResource("config_template.yml",false);
			File file2 = new File(getDataFolder(),"config_template.yml");
			file2.renameTo(new File(getDataFolder(),"config.yml"));
		}catch(Exception e) {}
	}

	getServer().getConsoleSender().sendMessage("§3[PDGHNoob] §2ativado - Plugin by: mchiappinam");
	getServer().getConsoleSender().sendMessage("§3[PDGHNoob] §2Acesse: http://pdgh.net/");
  }

  public void onDisable() {
	getServer().getConsoleSender().sendMessage("§3[PDGHNoob] §2desativado - Plugin by: mchiappinam");
	getServer().getConsoleSender().sendMessage("§3[PDGHNoob] §2Acesse: http://pdgh.net/");
  }

  @EventHandler(priority=EventPriority.HIGHEST)
  private void onChat(ChatMessageEvent e) {
    if ((e.getTags().contains("noob")) && (getConfig().getString("Noob").toLowerCase().trim().equalsIgnoreCase(e.getSender().getName().toLowerCase()))) {
    	e.setTagValue("noob", "§dⓃⓄⓄⒷ");
    }
  }

  @EventHandler(priority=EventPriority.HIGHEST)
  private void onDeath(PlayerDeathEvent e) {
    if ((e.getEntity().getKiller() instanceof Player)) {
      Player killed = e.getEntity();
      Player killer = e.getEntity().getKiller();
      if (getConfig().getString("Noob").toLowerCase().trim().equalsIgnoreCase(killer.getName().toLowerCase())) {
        getServer().broadcastMessage(" ");
        getServer().broadcastMessage("§d[ⓃⓄⓄⒷ] §l"+killed.getName()+" §cmorreu e virou o novo §dⓃⓄⓄⒷ§c!");
        getServer().broadcastMessage(" ");
        setNoob(killed.getName(), this);
      }
    }
  }

	public static void setNoob(String p, Main m) {
		m.getConfig().set("Noob", p);
		m.saveConfig();
		m.reloadConfig();
		Player pl = Bukkit.getPlayer(p);
		if(pl!=null) {
			pl.playEffect(pl.getLocation(), Effect.GHAST_SHOOT, 3);
			pl.playEffect(pl.getLocation(), Effect.GHAST_SHRIEK, 3);
			pl.playEffect(pl.getLocation(), Effect.MOBSPAWNER_FLAMES, 3);
			for (Player all : Bukkit.getServer().getOnlinePlayers()) {
				all.playSound(all.getLocation(), Sound.WOLF_DEATH, 1.0F, 1.0F);
			}
		}
	}
  
  
  
  
  
  
  
  
  
  
}