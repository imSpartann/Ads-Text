package com.acey.adstext;

import org.bukkit.plugin.java.JavaPlugin;

public class AdsTextPlugin extends JavaPlugin {

    private AdsScheduler scheduler;
    private JoinListener joinListener;
    private ChatBotListener chatBotListener;

    @Override
    public void onEnable() {
        // Save default configs on first run
        saveDefaultConfig();
        saveResource("ads.yml", false);
        saveResource("connect.yml", false);
        saveResource("chatbot.yml", false);

        // Register /ads command
        AdsCommand adsCommand = new AdsCommand(this);
        getCommand("ads").setExecutor(adsCommand);
        getCommand("ads").setTabCompleter(adsCommand);

        // Feature: Ads broadcasting
        if (getConfig().getBoolean("features.ads", true)) {
            scheduler = new AdsScheduler(this);
            scheduler.start();
        }

        // Feature: Welcome message
        if (getConfig().getBoolean("features.welcome-message", true)) {
            joinListener = new JoinListener(this);
            getServer().getPluginManager().registerEvents(joinListener, this);
        }

        // Feature: Chat auto-respond
        if (getConfig().getBoolean("features.auto-respond", true)) {
            chatBotListener = new ChatBotListener(this);
            getServer().getPluginManager().registerEvents(chatBotListener, this);
        }

        getLogger().info("Ads-text enabled.");
    }

    @Override
    public void onDisable() {
        if (scheduler != null) {
            scheduler.stop();
        }
        getLogger().info("Ads-text disabled.");
    }

    public void reloadAds() {
        reloadConfig();

        // Reload Ads broadcasting if enabled
        if (getConfig().getBoolean("features.ads", true)) {
            if (scheduler != null) scheduler.stop();
            scheduler = new AdsScheduler(this);
            scheduler.start();
        }

        // Reload Welcome messages if enabled
        if (getConfig().getBoolean("features.welcome-message", true) && joinListener != null) {
            joinListener.reload();
        }

        // Reload chatbot if enabled
        if (getConfig().getBoolean("features.auto-respond", true) && chatBotListener != null) {
            chatBotListener.reload();
        }
    }
}
