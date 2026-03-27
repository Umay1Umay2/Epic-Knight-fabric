# Epic Knights: Shields, Armor and Weapons - Fabric 1.21.4 Port

A comprehensive medieval weaponry, armor, and shields mod for Minecraft Fabric 1.21.4. This is a port of the original Epic Knights mod by [Magistu](https://github.com/Magistu/Epic-Knights) to the Fabric platform with enhanced features including an addon API, improved error handling, and automated testing.

[![Build Status](https://github.com/phantomhivealice200-stack/Epic-Knights_v1.21.4/actions/workflows/build.yml/badge.svg)](https://github.com/phantomhivealice200-stack/Epic-Knights_v1.21.4/actions)
[![License: All Rights Reserved](https://img.shields.io/badge/License-All%20Rights%20Reserved-red)]()
[![Minecraft Version](https://img.shields.io/badge/Minecraft-1.21.4-green)]()
[![Fabric](https://img.shields.io/badge/Modloader-Fabric-blue)]()

## Features

### 🛡️ Shields
- Over 10 shield variants (Heater, Kite, Pavise, Round, Buckler, Tartsche, and more)
- Shield patterns and heraldry decoration system
- Different visual designs for each material

### ⚔️ Weapons
- Diverse medieval weapons (swords, axes, halberds, pikes, lances, and more)
- Weapon-specific mechanics and balance
- Material variants and customizable designs

### 🏺 Armor
- Medieval armor sets with realistic designs
- Complete armor sets: Knights, Crusaders, Gothic, Ceremonial, and more
- Leggings, boots, helmets, and chestplates
- Horse armor variants

### 💎 Decorations
- Armor decorations and cosmetics
- Crowns, horns, plumes, and more headwear
- Accessories for personalizing your outfit

### 🔌 Addon System
Build extensions for Epic Knights with the novel addon API! See [Addon Development Guide](#addon-development) below.

## Installation

### Requirements
- **Minecraft**: 1.21.4
- **Fabric Loader**: 0.16.10 or higher
- **Java**: JDK 21 or later
- **Fabric API**: 0.112.2+1.21.4 or higher
- **Architectury API**: 13.0.8 or higher

### Quick Start

1. **Download the mod JAR**
   - Get the latest release from [GitHub Releases](https://github.com/phantomhivealice200-stack/Epic-Knights_v1.21.4/releases)
   - Or download from [Modrinth](https://modrinth.com/mod/epic-knights-shields-armor-and-weapons)

2. **Install to your Minecraft directory**
   ```bash
   # Copy the JAR to your mods folder
   cp epic-knights-<version>.jar ~/.minecraft/mods/
   cp fabric-api-<version>.jar ~/.minecraft/mods/
   cp architectury-<version>-fabric.jar ~/.minecraft/mods/
   ```

3. **Launch Minecraft**
   - Use a Fabric-compatible launcher
   - Medieval armor, weapons, and shields will be available in creative mode and as loot

## Build from Source

### Prerequisites
- **JDK 21** or later
- **Git**
- **Gradle** (use the included wrapper `./gradlew`)

### Build Steps

```bash
# Clone the repository
git clone https://github.com/phantomhivealice200-stack/Epic-Knights_v1.21.4.git
cd Epic-Knights_v1.21.4

# Switch to the Fabric port branch
git checkout fabric-1.21.4-port

# Build the project
./gradlew clean build

# Run tests
./gradlew test

# Test in-game
./gradlew runClient
```

The built JAR will be in `fabric/build/libs/`.

## Configuration

Epic Knights provides a configuration system via [Cloth Config](https://modrinth.com/mod/cloth-config) and [Mod Menu](https://modrinth.com/mod/modmenu).

**Configuration file location**: `.minecraft/config/magistuarmory.json`

**Available settings**:
- `general`: General mod settings
- `armor`: Armor balance and mechanics
- `weapons`: Weapon balance and mechanics
- `shields`: Shield mechanics and durability
- `mobEquipment`: Mob equipment distribution

Access configuration in-game via Mod Menu or edit the JSON file directly.

## Addon Development

Epic Knights includes a powerful addon system that allows third-party mods to extend it with custom items, blocks, recipes, and more.

### Quick Addon Example

```java
package io.example.epicknightssample;

import com.magistuarmory.addon.Addon;
import com.magistuarmory.addon.AddonRegistry;
import com.magistuarmory.addon.AddonClientRegistry;
import net.minecraft.world.item.Item;

public class SampleAddon implements Addon {
    
    @Override
    public String getAddonId() {
        return "example:sample";
    }
    
    @Override
    public String getAddonName() {
        return "Example Sample Addon";
    }
    
    @Override
    public String getAddonVersion() {
        return "1.0.0";
    }
    
    @Override
    public void onRegisterContent(AddonRegistry registry) {
        // Register your custom items and blocks here
        Item customSword = new Item(new Item.Properties());
        registry.registerItem("custom_sword", customSword);
    }
    
    @Override
    public void onClientInit(AddonClientRegistry clientRegistry) {
        // Register client-side content here (models, textures, etc.)
    }
    
    @Override
    public void onServerStart(AddonRegistry registry) {
        // Setup server-side logic
    }
}
```

### Register Your Addon

Create a file in your addon JAR at:
```
META-INF/services/com.magistuarmory.addon.Addon
```

Add your addon class name:
```
io.example.epicknightssample.SampleAddon
```

### Full Addon Guide

See [`ADDON_DEVELOPMENT.md`](./ADDON_DEVELOPMENT.md) for detailed documentation on:
- Registering items, blocks, and recipes
- Working with armor and weapons
- Creating custom models and textures
- Server-side and client-side initialization
- Error handling and logging
- Publishing your addon

## Contributing

Contributions are welcome! Please follow these guidelines:

1. **Fork the repository** and create a feature branch
   ```bash
   git checkout -b feature/your-feature
   ```

2. **Make your changes** and write tests
   ```bash
   ./gradlew test
   ```

3. **Commit with clear messages**
   ```bash
   git commit -m "feat: add new armor type"
   ```

4. **Submit a Pull Request** using the [PR template](.github/pull_request_template.md)

For more details, see [`CONTRIBUTING.md`](./CONTRIBUTING.md).

## Project Structure

```
Epic-Knights_v1.21.4/
├── common/                    # Shared code (all platforms)
│   ├── src/main/java/        # Source code
│   │   └── com/magistuarmory/
│   │       ├── addon/        # Addon API system
│   │       ├── item/         # Item definitions
│   │       ├── block/        # Block definitions
│   │       ├── event/        # Event handlers
│   │       ├── client/       # Client-side utilities
│   │       └── util/         # Utilities
│   └── src/test/java/        # Unit tests
├── fabric/                     # Fabric-specific code
│   ├── src/main/java/        # Fabric platform code
│   └── src/main/resources/    # Fabric configuration
├── .github/workflows/         # CI/CD automation
├── build.gradle              # Root Gradle config
├── gradle.properties         # Gradle properties
└── README.md                 # This file
```

## Dependencies

This project uses Architectury API as the abstraction layer for cross-platform support.

### Required

- **Fabric Loader** 0.16.10+
- **Fabric API** 0.112.2+1.21.4
- **Architectury API** 13.0.8
- **Cloth Config** 15.0.128 (for configuration UI)

### Optional

- **Mod Menu** 11.0.1 (for in-game config access)
- **Better Combat** or **Epic Fight** (for enhanced combat mechanics)

## References

- **Original Project**: [GitHub - Magistu/Epic-Knights](https://github.com/Magistu/Epic-Knights)
- **Modrinth Listing**: [Epic Knights - Shields, Armor and Weapons](https://modrinth.com/mod/epic-knights-shields-armor-and-weapons/versions?g=1.21.1&l=fabric)
- **Fabric API**: [Modrinth](https://modrinth.com/mod/fabric-api/versions?c=release&g=1.21.4) | [GitHub](https://github.com/FabricMC/fabric-api)
- **Architectury API**: [Modrinth](https://modrinth.com/mod/architectury-api/versions?g=1.21.4&l=fabric) | [GitHub](https://github.com/architectury/architectury-api)
- **Cloth Config**: [Modrinth](https://modrinth.com/mod/cloth-config)
- **Mod Menu**: [Modrinth](https://modrinth.com/mod/modmenu)

## License

All Rights Reserved - See LICENSE file for details

## Credits

- **Original Author**: [Magistu](https://github.com/Magistu)
- **Contributors**: StaxiHD, Mino, Lexiolty, Jackiecrazy, CrazyBarkley, Ryuje, Witchkings, Cole3050, taizazanek, thyreo
- **Fabric Port**: [phantomhivealice200-stack](https://github.com/phantomhivealice200-stack)

## Support

- **Issues**: [GitHub Issues](https://github.com/phantomhivealice200-stack/Epic-Knights_v1.21.4/issues)
- **Discord**: [Join our Discord](https://discord.gg/H9CVcXEmYe)
- **Original Mod's CurseForge**: [Epic Knights on CurseForge](https://www.curseforge.com/minecraft/mc-mods/epic-knights-armor-and-weapons)

## Troubleshooting

### Mod doesn't load
- Ensure you have **Fabric Loader 0.16.10** or higher installed
- Check that **Fabric API** is installed
- Verify **Architectury API** is present in your mods folder
- Review logs in `.minecraft/logs/latest.log`

### Missing textures or models
- Delete your shader cache: `.minecraft/shadercache`
- Regenerate ResourcePack caches
- Re-download and reinstall the mod JAR

### Performance issues
- Check your graphics settings in Minecraft
- Reduce render distance if needed
- Disable shader-heavy resource packs temporarily
- File an issue with system specs for investigation

### Crashes or errors
- Enable debug mode in mod config (see Configuration section)
- Collect diagnostics from `logs/epicknights-diagnostics.txt`
- File an issue with the complete log and crash report

## Changelog

See [CHANGELOG.md](./CHANGELOG.md) for version history and detailed changes.

---

**Happy crafting!** 🛡️⚔️
