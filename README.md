# Ads-Text

**Ads-Text** is a lightweight, configurable Minecraft plugin that automates server communication through scheduled broadcasts, welcome messages, and smart chat auto-responses. Designed for ease of use and full customization, it enhances player interaction without requiring complex setup.

## Features

- **Automated Broadcasts**  
  Display configurable messages to all players at set intervals with optional clickable links.

- **Welcome Messages**  
  Send a custom message to each player when they join the server, supporting color codes and link components.

- **Smart Chat Auto-Respond**  
  Detects keywords in player chat and replies with predefined responses loaded from a YAML file.

- **Full Config Reload Support**  
  Modify settings and messages without restarting the server using `/ads reload`.

- **Modular Feature Control**  
  Enable or disable ads, welcome messages, or auto-respond functionality through `config.yml`.

## Configuration

The plugin uses multiple YAML files for configuration:

- `config.yml`: Controls plugin settings like interval timing and feature toggles.
- `ads.yml`: List of broadcast messages.
- `connect.yml`: Messages shown when players join.
- `chatbot.yml`: Keyword-to-response mappings for chat triggers.

### Example: `config.yml`

```yaml
features:
  ads: true
  welcome-message: true
  auto-respond: true

interval-minutes: 10

chatbot-response-delay-ticks: 1
```
## Commands

| Command       | Description                 | Permission       |
|---------------|-----------------------------|------------------|
| /ads reload   | Reloads all plugin configs  | adstext.reload   |

## Permissions

```yaml
adstext.reload:
  description: Allows use of /ads reload
  default: op
```

## Installation

1. Place the compiled `Ads-Text.jar` into your server's `/plugins` directory.
2. Start the server to generate default config files.
3. Edit `ads.yml`, `connect.yml`, and `chatbot.yml` to fit your server style.
4. Use `/ads reload` to apply changes without restarting.

## Compatibility

- Paper 1.21.4 and above
- Spigot 1.21.4 and above

## License

This project is licensed under the MIT License.

## Contributors

- Acey ([imSpartann](https://github.com/imSpartann))

