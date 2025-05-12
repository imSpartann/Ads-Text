# 📢 Ads-Text

**Ads-Text** is a lightweight plugin that lets you broadcast messages, send welcome messages, and auto-respond to chat using customizable YAML files.  
Easy to configure, reloadable, and perfect for any Paper or Spigot server.

Broadcast & Auto Respond messages support clickable `http/https` links, which open in the player's web browser when clicked (must be wrapped in `<` and `>`).

👉 **[View the full Wiki here »](https://github.com/imSpartann/Ads-Text/wiki)**

---

## 📂 Configuration Files

| File           | Description                                                   |
|----------------|---------------------------------------------------------------|
| `config.yml`   | Controls feature toggles, broadcast interval, chatbot delay   |
| `ads.yml`      | Contains scheduled messages to broadcast to all players       |
| `connect.yml`  | Stores the welcome message sent to players on join            |
| `chatbot.yml`  | Keyword-based triggers and responses for chat auto-replies    |

---

## ⚙️ Example `config.yml`

```yaml
# Enable or disable the features of the plugin.
features:
  ads: true
  welcome-message: true
  auto-respond: true

# How often to Ads-broadcast messages (in minutes)
interval-minutes: 10

# Chatbot response delay in ticks.
# 20 ticks = 1 second
chatbot-response-delay-ticks: 1
```

---

## 💬 Commands

| Command       | Description                  |
|---------------|------------------------------|
| `/ads reload` | Reloads all plugin configs   |

---

## 🔐 Permissions

```yaml
adstext.reload:
  description: Allows use of /ads reload
  default: op
```

---

## 🚀 Setup Instructions

1. Place the plugin `.jar` file into your server’s `/plugins` folder.
2. Start the server to generate the configuration files.
3. Edit the YAML files to customize your messages and settings.
4. Use `/ads reload` to apply changes without restarting (feature toggles may still require restart).

---

## 🧾 Requirements

- ✅ Minecraft Server: Paper or Spigot **1.21.x**
- ✅ Java Version: **21 or higher**

---

## ⭐ Support the Project

If you enjoy using this plugin, please:
- ⭐ **Rate it 5 stars** on [SpigotMC](https://www.spigotmc.org/resources/ads-text.124798/)
- 📝 Leave a comment or feedback
- 🤝 [Submit issues or contribute on GitHub](https://github.com/imSpartann/Ads-Text/issues)
