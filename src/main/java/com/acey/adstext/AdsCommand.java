package com.acey.adstext;

import org.bukkit.ChatColor;
import org.bukkit.command.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdsCommand implements CommandExecutor, TabCompleter {

    private final AdsTextPlugin plugin;

    public AdsCommand(AdsTextPlugin plugin) {
        this.plugin = plugin;
    }

    // Handle /ads reload
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("adstext.reload")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
                return true;
            }

            plugin.reloadAds();
            sender.sendMessage(ChatColor.GREEN + "Ads-text config and messages reloaded.");
            return true;
        }

        sender.sendMessage(ChatColor.YELLOW + "Usage: /ads reload");
        return true;
    }

    // Tab complete for /ads <reload>
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> completions = new ArrayList<>();
            if ("reload".startsWith(args[0].toLowerCase())) {
                completions.add("reload");
            }
            return completions;
        }
        return Collections.emptyList();
    }
}
