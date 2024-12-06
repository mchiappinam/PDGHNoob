package me.mchiappinam.pdghnoob;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Comando implements CommandExecutor {
  private Main plugin;

  public Comando(Main main) {
    plugin = main;
  }

  public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
    if (cmd.getName().equalsIgnoreCase("noob")) {
        if (args.length >0) {
            sender.sendMessage("§cUse /noob");
            return true;
          }
      if (plugin.getConfig().getString("Noob").length() > 0) {
        sender.sendMessage("§d[ⓃⓄⓄⒷ] §l"+plugin.getConfig().getString("Noob")+" §cé o §dⓃⓄⓄⒷ §catual.");
      }else{
        sender.sendMessage("§d[ⓃⓄⓄⒷ] §cSem §dⓃⓄⓄⒷ §catual.");
      return true;
      }
    }
    if (cmd.getName().equalsIgnoreCase("setnoob")) {
      if (!sender.hasPermission("pdgh.admin")) {
        sender.sendMessage("§cSem permissões");
        return true;
      }
      if ((args.length >1) || (args.length == 0)) {
        sender.sendMessage("§cUse /setnoob <nick>");
        return true;
      }
      Main.setNoob(args[0], plugin);
      plugin.getServer().broadcastMessage(" ");
      plugin.getServer().broadcastMessage("§d[ⓃⓄⓄⒷ] §l"+args[0]+" §cé o novo §dⓃⓄⓄⒷ");
      plugin.getServer().broadcastMessage(" ");
      
      //Main.setNoob(args[0], plugin);
      return true;
    }
    return true;
  }
}