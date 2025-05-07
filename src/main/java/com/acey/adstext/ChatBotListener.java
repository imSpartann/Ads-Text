package com.acey.adstext;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatBotListener implements Listener {

    private final AdsTextPlugin plugin;
    private Map<String, List<String>> responses;
    private int responseDelayTicks = 1;

    private static final Pattern LINK_PATTERN = Pattern.compile("<(https?://[^>]+)>", Pattern.CASE_INSENSITIVE);

    public ChatBotListener(AdsTextPlugin plugin) {
        this.plugin = plugin;
        responseDelayTicks = plugin.getConfig().getInt("chatbot-response-delay-ticks", 1);
        loadResponses();
    }

    public void loadResponses() {
        File file = new File(plugin.getDataFolder(), "chatbot.yml");
        if (!file.exists()) {
            plugin.saveResource("chatbot.yml", false);
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        responses = (Map<String, List<String>>) (Map<?, ?>) config.getConfigurationSection("responses").getValues(false);
    }

    public void reload() {
        responseDelayTicks = plugin.getConfig().getInt("chatbot-response-delay-ticks", 1);
        loadResponses();
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage().toLowerCase();
        Player player = event.getPlayer();

        for (String key : responses.keySet()) {
            if (message.contains(key.toLowerCase())) {
                List<String> replies = responses.get(key);
                plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                    for (String response : replies) {
                        String formatted = ChatColor.translateAlternateColorCodes('&', response)
                                .replace("%player%", player.getName());

                        Matcher matcher = LINK_PATTERN.matcher(formatted);
                        if (matcher.find()) {
                            String url = matcher.group(1);
                            TextComponent clickable = new TextComponent(formatted);
                            clickable.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
                            player.spigot().sendMessage(clickable);
                        } else {
                            player.sendMessage(formatted);
                        }
                    }
                }, responseDelayTicks);
                break;
            }
        }
    }
}
