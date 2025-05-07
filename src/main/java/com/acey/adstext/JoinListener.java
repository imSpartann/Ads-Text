package com.acey.adstext;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JoinListener implements Listener {

    private final AdsTextPlugin plugin;
    private List<String> messages;

    private static final Pattern LINK_PATTERN = Pattern.compile("<(https?://[^>]+)>");

    public JoinListener(AdsTextPlugin plugin) {
        this.plugin = plugin;
        loadMessages();
    }

    private void loadMessages() {
        File file = new File(plugin.getDataFolder(), "connect.yml");
        if (!file.exists()) {
            plugin.saveResource("connect.yml", false);
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        messages = config.getStringList("messages");
    }

    public void reload() {
        loadMessages(); // Allow reload from /ads reload
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (messages == null || messages.isEmpty()) return;

        Player player = event.getPlayer();

        for (String msg : messages) {
            Matcher matcher = LINK_PATTERN.matcher(msg);
            if (matcher.find()) {
                String url = matcher.group(1); // URL inside < >
                String withoutBrackets = msg.replace("<" + url + ">", url);
                String colored = ChatColor.translateAlternateColorCodes('&', withoutBrackets);

                TextComponent component = new TextComponent(colored);
                component.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));

                player.spigot().sendMessage(component);
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
            }
        }
    }
}
