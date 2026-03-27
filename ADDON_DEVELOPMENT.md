# Epic Knights Addon Development Guide

This guide covers how to develop addons for Epic Knights using the addon API.

## Table of Contents

1. [Getting Started](#getting-started)
2. [Project Setup](#project-setup)
3. [Basic Addon Structure](#basic-addon-structure)
4. [Registering Content](#registering-content)
5. [Client-Side Features](#client-side-features)
6. [Server-Side Features](#server-side-features)
7. [Publishing Your Addon](#publishing-your-addon)
8. [Best Practices](#best-practices)
9. [Examples](#examples)

## Getting Started

Epic Knights provides an addon system that allows you to extend the mod with your own items, blocks, recipes, and mechanics.

### What Can Addons Do?

- Register custom items and blocks
- Define recipes and crafting recipes
- Add custom armor and weapons
- Implement new mechanics using events
- Register models and textures
- Extend combat capabilities
- Add new decorative elements

### What Addons Cannot Do

- Modify core mod behavior (by design, for stability)
- Access private mod internals
- Directly register game content outside the addon API
- Modify other mod's content directly

## Project Setup

### Requirements

- **Java 17+** (recommended 21)
- **Gradle** or Maven build system
- Epic Knights JAR as a dependency

### Gradle Project Template

Create a new Gradle project with the following `build.gradle`:

```gradle
plugins {
    id 'java'
}

repositories {
    mavenCentral()
    maven { url "https://maven.terraformersmc.com/" }
    maven { url "https://maven.fabricmc.net/" }
    maven { url "https://maven.architectury.dev/" }
}

dependencies {
    // Minecraft and Fabric
    implementation "net.minecraft:minecraft:1.21.4"
    implementation "net.fabricmc:fabric-loader:0.16.10"
    implementation "net.fabricmc.fabric-api:fabric-api:0.112.2+1.21.4"
    
    // Architectury
    implementation "dev.architectury:architectury-fabric:13.0.8"
    
    // Epic Knights API (replace with actual repo/version once published)
    implementation "io.github.magistu:epic-knights:10.6"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
```

## Basic Addon Structure

### 1. Create Your Addon Class

All addons must implement the `Addon` interface:

```java
package com.example.mymod;

import com.magistuarmory.addon.Addon;
import com.magistuarmory.addon.AddonRegistry;
import com.magistuarmory.addon.AddonClientRegistry;

public class MyAddon implements Addon {
    
    @Override
    public String getAddonId() {
        return "mymod:myaddon";
    }
    
    @Override
    public String getAddonName() {
        return "My Awesome Addon";
    }
    
    @Override
    public String getAddonVersion() {
        return "1.0.0";
    }
    
    @Override
    public void onRegisterContent(AddonRegistry registry) {
        // Register items, blocks, recipes here
    }
    
    @Override
    public void onClientInit(AddonClientRegistry clientRegistry) {
        // Register client-side content (models, renderers)
    }
    
    @Override
    public void onServerStart(AddonRegistry registry) {
        // Setup server-side logic and listeners
    }
}
```

### 2. Register Your Addon

Create the file `META-INF/services/com.magistuarmory.addon.Addon` in your JAR's resources folder:

```
com.example.mymod.MyAddon
```

This uses Java's `ServiceLoader` mechanism to automatically discover your addon.

## Registering Content

### Registering Items

```java
@Override
public void onRegisterContent(AddonRegistry registry) {
    // Create item with default properties
    Item myItem = new Item(new Item.Properties());
    registry.registerItem("my_item", myItem);
    
    // Create armor
    ArmorMaterial myArmor = new ArmorMaterial(...);
    Item myHelmet = new ArmorItem(myArmor, ArmorItem.Type.HELMET, 
        new Item.Properties());
    registry.registerItem("my_helmet", myHelmet);
    
    // Create tools
    Item mySword = new SwordItem(Tiers.IRON, 3, -2.4f, 
        new Item.Properties());
    registry.registerItem("my_sword", mySword);
}
```

### Registering Blocks

```java
@Override
public void onRegisterContent(AddonRegistry registry) {
    Block myBlock = new Block(BlockBehaviour.Properties.of()
        .mapColor(MapColor.STONE)
        .strength(2.0f, 3.0f));
    registry.registerBlock("my_block", myBlock);
    
    // Register block with block entity
    registry.registerBlockWithEntity("my_entity_block", myBlock, "my_entity");
}
```

### Registering Recipes

For recipes, you'll typically want to add JSON files to your resources:

```
src/main/resources/data/mymod/recipes/
  ├── my_recipe.json
  └── my_shaped_recipe.json
```

Example recipe JSON:

```json
{
  "type": "crafting_shaped",
  "pattern": [
    "SSS",
    "S S",
    "SSS"
  ],
  "key": {
    "S": {
      "item": "minecraft:iron_ingot"
    }
  },
  "result": {
    "item": "mymod:my_item",
    "count": 1
  }
}
```

## Client-Side Features

### Registering Models

```java
@Override
public void onClientInit(AddonClientRegistry clientRegistry) {
    // Register a simple item model
    clientRegistry.registerModelLayer("my_helmet_model");
    
    // Models should be placed in:
    // src/main/resources/assets/mymod/models/item/my_item.json
}
```

### Custom Model Classes

For complex armor or cosmetics similar to Epic Knights:

```java
package com.example.mymod.client;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public class MyArmorModel extends HumanoidModel<LivingEntity> {
    
    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        LayerDefinition definitionBuilder = LayerDefinition.create(
            meshdefinition, 64, 64
        );
        // Build your model here
        return definitionBuilder;
    }
    
    public MyArmorModel(ModelPart modelPart) {
        super(modelPart);
    }
}
```

## Server-Side Features

### Event Listeners

Listen to game events using Architectury Events:

```java
@Override
public void onServerStart(AddonRegistry registry) {
    // Listen for entity spawn
    EntityEvent.ADD.register(entity -> {
        if (entity instanceof Mob mob && shouldEquip(mob)) {
            mob.setItemSlot(EquipmentSlot.HEAD, 
                new ItemStack(ModItems.MY_HELMET));
        }
    });
}
```

### Data Reload Listeners

```java
@Override
public void onServerStart(AddonRegistry registry) {
    // Register a reload listener for custom data
    // See Fabric API docs for ReloadListenerRegistry usage
}
```

## Publishing Your Addon

### Package Your Addon

1. Build your JAR:
   ```bash
   ./gradlew build
   ```

2. The JAR will be in `build/libs/myaddon-1.0.0.jar`

3. Ensure it includes:
   - All compiled classes
   - `META-INF/services/com.magistuarmory.addon.Addon` with your class name
   - All resources (models, textures, sounds, etc.)

### Distributing

You can distribute your addon through:

- **GitHub Releases**: Upload your JAR
- **CurseForge**: Create a project and upload
- **Modrinth**: Submit your addon as a separate mod entry
- **Direct download**: Host on your own website

### Documentation

Provide users with:

- Clear README with features and installation
- Build instructions
- Configuration options
- Known compatibility issues
- Support contact information

## Best Practices

### 1. Error Handling

Always wrap your code in try-catch blocks:

```java
@Override
public void onRegisterContent(AddonRegistry registry) {
    try {
        Item item = createItem();
        registry.registerItem("my_item", item);
    } catch (Exception e) {
        // Log error but don't crash
        System.err.println("Failed to register item: " + e.getMessage());
    }
}
```

### 2. Naming Conventions

- Use your addon namespace in all resource names
- Keep addon IDs lowercase with underscores
- Use descriptive names for features

```java
public String getAddonId() {
    return "mycompany:myfeature";  // good
    // NOT "MyFeature" or "my-feature"
}
```

### 3. Resource Organization

```
src/main/resources/
├── assets/mymod/
│   ├── models/
│   │   ├── item/
│   │   └── block/
│   ├── textures/
│   │   ├── item/
│   │   └── block/
│   ├── lang/
│   │   └── en_us.json
│   └── sounds.json
└── data/mymod/
    ├── recipes/
    ├── loot_tables/
    └── tags/
```

### 4. Testing

Test your addon thoroughly:

```bash
# Place your JAR in a test Minecraft instance
cp build/libs/myaddon-*.jar /path/to/.minecraft/mods/

# Test in-game
# Launch Minecraft and verify content works
```

### 5. Versioning

Follow semantic versioning:

```
MAJOR.MINOR.PATCH
1.0.0
  ↑ Major: Breaking changes
    ↑ Minor: New features (backward compatible)
      ↑ Patch: Bug fixes
```

## Examples

### Complete Simple Addon

```java
package io.github.example;

import com.magistuarmory.addon.Addon;
import com.magistuarmory.addon.AddonRegistry;
import com.magistuarmory.addon.AddonClientRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;

public class SimpleAddon implements Addon {
    
    public static final String ID = "example:simple";
    
    @Override
    public String getAddonId() {
        return ID;
    }
    
    @Override
    public String getAddonName() {
        return "Simple Example Addon";
    }
    
    @Override
    public String getAddonVersion() {
        return "1.0.0";
    }
    
    @Override
    public void onRegisterContent(AddonRegistry registry) {
        // Register a simple sword
        Item simpleSword = new SwordItem(Tiers.IRON, 5, -1.5f,
            new Item.Properties());
        registry.registerItem("simple_sword", simpleSword);
    }
    
    @Override
    public void onClientInit(AddonClientRegistry clientRegistry) {
        // Client initialization if needed
    }
    
    @Override
    public void onServerStart(AddonRegistry registry) {
        // Server initialization if needed
    }
}
```

### With Service Loader Configuration

File: `src/main/resources/META-INF/services/com.magistuarmory.addon.Addon`

```
io.github.example.SimpleAddon
```

## Troubleshooting

### Addon Not Loading

1. Check that your class implements `Addon` interface
2. Verify `META-INF/services/com.magistuarmory.addon.Addon` exists
3. Check the fully qualified class name is correct
4. Review server logs for error messages

### ClassNotFoundException

- Ensure Epic Knights is installed
- Check your JAR includes all necessary dependencies
- Verify your classpath in build configuration

### Items/Blocks Not Appearing

- Confirm registration in `onRegisterContent()`
- Check namespace is consistent across all references
- Verify JSON files for models and textures
- Use `/reload` command to refresh

## Resources

- **Fabric Wiki**: https://fabricmc.net/develop/
- **Minecraft Wiki**: https://minecraft.fandom.com/
- **Architectury Docs**: https://docs.architectury.dev/
- **Epic Knights GitHub**: https://github.com/Magistu/Epic-Knights
- **Fabric API Reference**: https://github.com/FabricMC/fabric-api

---

Happy addon development! 🎮✨
