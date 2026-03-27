package com.magistuarmory.addon;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

/**
 * Registry for addon content like items and blocks.
 * Use this to register your custom items, blocks, and other content.
 */
public interface AddonRegistry {
    
    /**
     * Registers a custom item.
     * @param name the item name (namespace is handled automatically)
     * @param item the Item instance
     * @return the registered item
     */
    Item registerItem(String name, Item item);
    
    /**
     * Registers a custom block.
     * @param name the block name (namespace is handled automatically)
     * @param block the Block instance
     * @return the registered block
     */
    Block registerBlock(String name, Block block);
    
    /**
     * Registers a block with an associated block entity.
     * @param name the block name
     * @param block the Block instance
     * @param blockEntityName the block entity name
     * @return the registered block
     */
    Block registerBlockWithEntity(String name, Block block, String blockEntityName);
    
    /**
     * Gets the namespace/mod id of this addon's registrations.
     * @return the mod ID
     */
    String getNamespace();
    
    /**
     * Creates a ResourceLocation for addon content.
     * @param name the resource name
     * @return a ResourceLocation with the addon's namespace
     */
    default ResourceLocation resourceLocation(String name) {
        return ResourceLocation.fromNamespaceAndPath(getNamespace(), name);
    }
    
    /**
     * Creates an Item from a source item with custom properties.
     * This is a helper for creating item variants.
     * @param sourceItem the base item to copy properties from
     * @param customProperties additional Item.Properties
     * @return a new Item instance
     */
    default Item createItem(Item sourceItem, Item.Properties customProperties) {
        return new Item(customProperties);
    }
}
