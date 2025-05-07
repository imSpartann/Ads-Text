package com.acey.adstext;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdsScheduler {

    private final JavaPlugin plugin;
    private int taskId = -1;
    private long intervalMinutes = 10;
    private List<String> messages;

    // Regex to detect clickable URL in < >
    private static final Pattern LINK_PATTERN = Pattern.compile("<(https?://[^>]+)>");

    public AdsScheduler(JavaPlugin plugin) {
        this.plugin = plugin;
        this.intervalMinutes = plugin.getConfig().getLong("interval-minutes", 10);
        loadMessagesFromAdsYml();
    }

    private void loadMessagesFromAdsYml() {
        File adsFile = new File(plugin.getDataFolder(), "ads.yml");
        FileConfiguration adsConfig = YamlConfiguration.loadConfiguration(adsFile);
        messages = adsConfig.getStringList("messages");
    }

    public void start() {
        if (messages == null || messages.isEmpty()) {
            plugin.getLogger().warning("No messages found in ads.yml!");
            return;
        }

        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            private int index = 0;

            @Override
            public void run() {
                String rawMsg = messages.get(index % messages.size());
                String formatted = ChatColor.translateAlternateColorCodes('&', rawMsg);

                Matcher matcher = LINK_PATTERN.matcher(rawMsg);
                if (matcher.find()) {
                    String url = matcher.group(1); // the URL inside <>
                    String withoutBrackets = rawMsg.replace("<" + url + ">", url);
                    String coloredText = ChatColor.translateAlternateColorCodes('&', withoutBrackets);

                    TextComponent component = new TextComponent(coloredText);
                    component.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));

                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.spigot().sendMessage(component);
                    }
                } else {
                    // Fallback: normal broadcast
                    Bukkit.broadcastMessage(formatted);
                }
                index++;
            }
        }, 0L, intervalMinutes * 60 * 20);
    }

    public void stop() {
        if (taskId != -1) {
            Bukkit.getScheduler().cancelTask(taskId);
        }
    }

    public long getIntervalMinutes() {
        return intervalMinutes;
    }
}
