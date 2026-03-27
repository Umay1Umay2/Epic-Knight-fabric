package io.github.example.epicknightsaddon;

import com.magistuarmory.addon.Addon;
import com.magistuarmory.addon.AddonRegistry;
import com.magistuarmory.addon.AddonClientRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Example Epic Knights addon demonstrating the addon API.
 * 
 * This addon adds:
 * - A custom sword
 * - A custom block
 * - Demonstrates lifecycle methods
 */
public class ExampleAddon implements Addon {
    
    private static final Logger LOGGER = LoggerFactory.getLogger("ExampleAddon");
    
    public static final String ADDON_ID = "example:addon";
    
    @Override
    public String getAddonId() {
        return ADDON_ID;
    }
    
    @Override
    public String getAddonName() {
        return "Example Addon for Epic Knights";
    }
    
    @Override
    public String getAddonVersion() {
        return "1.0.0";
    }
    
    @Override
    public void onRegisterContent(AddonRegistry registry) {
        LOGGER.info("Registering Example Addon content...");
        
        try {
            // Register a custom sword
            Item exampleSword = new SwordItem(
                Tiers.IRON,
                5,  // Attack damage bonus
                -1.5f,  // Attack speed
                new Item.Properties()
            );
            registry.registerItem("example_sword", exampleSword);
            LOGGER.info("Registered example_sword");
            
            // Register a custom block
            Block exampleBlock = new Block(BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                .strength(2.0f, 6.0f)
                .requiresCorrectToolForDrops()
            );
            registry.registerBlock("example_block", exampleBlock);
            LOGGER.info("Registered example_block");
            
            // Register a block item
            Item exampleBlockItem = new BlockItem(
                exampleBlock,
                new Item.Properties()
            );
            registry.registerItem("example_block_item", exampleBlockItem);
            LOGGER.info("Registered example_block_item");
            
        } catch (Exception e) {
            LOGGER.error("Failed to register addon content", e);
        }
    }
    
    @Override
    public void onClientInit(AddonClientRegistry clientRegistry) {
        LOGGER.info("Initializing Example Addon client features...");
        
        try {
            // You can register client-side models, textures, etc. here
            // clientRegistry.registerModelLayer("example_sword_model");
            
            LOGGER.info("Example Addon client initialization complete");
        } catch (Exception e) {
            LOGGER.error("Failed to initialize client features", e);
        }
    }
    
    @Override
    public void onServerStart(AddonRegistry registry) {
        LOGGER.info("Example Addon server initialization...");
        
        try {
            // You can set up event listeners, data loaders, etc. here
            // EventName.EVENT.register(yourListener);
            
            LOGGER.info("Example Addon server initialization complete");
        } catch (Exception e) {
            LOGGER.error("Failed to initialize server features", e);
        }
    }
}
