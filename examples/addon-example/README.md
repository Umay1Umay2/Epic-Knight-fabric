# Example Epic Knights Addon

A minimal but complete example addon for Epic Knights demonstrating the addon API.

## Features

This addon demonstrates:
- ✅ Registering custom items (sword)
- ✅ Registering custom blocks
- ✅ Using the addon lifecycle methods
- ✅ Proper error handling
- ✅ Logging best practices
- ✅ Localization/i18n

## What This Addon Adds

- **Example Sword**: A custom iron-tier sword
- **Example Block**: A custom stone-like block
- Both are registered with proper localization

## Building

```bash
cd examples/addon-example
gradle build
```

The JAR will be created at `build/libs/addon-example-1.0.0.jar`

## Installation

1. Build the addon (see above)
2. Copy the JAR to your Minecraft mods folder:
   ```bash
   cp build/libs/addon-example-*.jar ~/.minecraft/mods/
   ```
3. Ensure Epic Knights is also installed
4. Launch Minecraft with Fabric Loader

## Verifying Installation

1. Launch Minecraft in creative mode
2. Open the creative inventory
3. Search for "example_" to find the addon items
4. Check server logs for messages like:
   ```
   [ExampleAddon] Registering Example Addon content...
   [ExampleAddon] Registered example_sword
   ```

## Code Structure

```
src/
├── main/
│   ├── java/
│   │   └── io/github/example/epicknightsaddon/
│   │       └── ExampleAddon.java           # Main addon class
│   └── resources/
│       ├── META-INF/services/
│       │   └── com.magistuarmory.addon.Addon  # Service loader config
│       └── assets/example-addon/
│           └── lang/
│               └── en_us.json              # English localization
```

## Key Components

### ExampleAddon.java

The main addon class implementing `Addon`:

- `getAddonId()`: Returns `"example:addon"`
- `getAddonName()`: Returns human-readable name
- `getAddonVersion()`: Returns version
- `onRegisterContent()`: Registers items and blocks
- `onClientInit()`: Client-side initialization
- `onServerStart()`: Server-side initialization

### Service Loader Configuration

File: `META-INF/services/com.magistuarmory.addon.Addon`

Contains the fully qualified class name for automatic discovery:
```
io.github.example.epicknightsaddon.ExampleAddon
```

### Localization

File: `assets/example-addon/lang/en_us.json`

Defines display names for items and blocks:
```json
{
  "item.example.addon.example_sword": "Example Sword",
  "block.example.addon.example_block": "Example Block"
}
```

## Extending This Example

To use this as a template for your own addon:

1. **Rename the addon**:
   - Update addon ID in `ExampleAddon.getAddonId()`
   - Update class name and package
   - Update localization namespace

2. **Add your items**:
   ```java
   Item yourItem = new SwordItem(...);
   registry.registerItem("your_item", yourItem);
   ```

3. **Add blocks**:
   ```java
   Block yourBlock = new Block(...);
   registry.registerBlock("your_block", yourBlock);
   ```

4. **Create models**:
   - Add JSON models in `assets/your-addon/models/item/`
   - Add JSON models in `assets/your-addon/models/block/`

5. **Add textures**:
   - PNG files in `assets/your-addon/textures/item/`
   - PNG files in `assets/your-addon/textures/block/`

6. **Add localization**:
   - Update `en_us.json` with your item/block names
   - Add other language files as needed

## Testing

### In Creative Mode

1. Run the mod
2. Access creative inventory
3. Search for "example_"
4. Verify items appear and are named correctly

### In Survival Mode

1. Place the example block
2. Use the example sword to verify it works
3. Check durability and tool tier

### Logging

The addon logs its initialization:

```
[ExampleAddon] Registering Example Addon content...
[ExampleAddon] Registered example_sword
[ExampleAddon] Registered example_block
[ExampleAddon] Initializing Example Addon client features...
```

Check logs at `.minecraft/logs/latest.log`

## Learning Resources

- [Addon Development Guide](../../ADDON_DEVELOPMENT.md)
- [Epic Knights README](../../README.md)
- [Fabric Wiki](https://fabricmc.net/develop/)
- [Minecraft Wiki](https://minecraft.fandom.com/)

## License

This example addon is provided as-is for learning purposes.

---

Happy addon development! 🎮✨
