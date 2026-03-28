package com.magistuarmory.addon;

import com.magistuarmory.util.EpicKnightsLogger;
import dev.architectury.platform.Platform;
import net.fabricmc.api.EnvType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Loader for Epic Knights addons.
 * 
 * Discovers and loads addons using Java's ServiceLoader mechanism.
 * Addons should register themselves in META-INF/services/com.magistuarmory.addon.Addon
 */
public class AddonLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger("EpicKnights/AddonLoader");
    
    private static final List<Addon> LOADED_ADDONS = new ArrayList<>();
    private static final Map<String, Addon> ADDON_MAP = new HashMap<>();
    
    /**
     * Discovers and loads all available addons.
     * This should be called during the common init phase.
     */
    public static void loadAddons() {
        try {
            ServiceLoader<Addon> loader = ServiceLoader.load(Addon.class);
            
            for (Addon addon : loader) {
                try {
                    String addonId = addon.getAddonId();
                    
                    // Check for duplicate addon IDs
                    if (ADDON_MAP.containsKey(addonId)) {
                        LOGGER.error("Duplicate addon ID detected: {}. Skipping addon: {}", 
                            addonId, addon.getAddonName());
                        continue;
                    }
                    
                    LOADED_ADDONS.add(addon);
                    ADDON_MAP.put(addonId, addon);
                    
                    LOGGER.info("Loaded addon: {} v{} ({})", 
                        addon.getAddonName(), addon.getAddonVersion(), addon.getAddonId());
                    
                } catch (Exception e) {
                    LOGGER.error("Failed to load addon. This addon will be skipped.", e);
                }
            }
            
            if (LOADED_ADDONS.isEmpty()) {
                LOGGER.info("No addons found");
            } else {
                LOGGER.info("Successfully loaded {} addon(s)", LOADED_ADDONS.size());
            }
            
        } catch (Exception e) {
            LOGGER.error("Failed to load addons. Any registered addons will be unavailable.", e);
        }
    }
    
    /**
     * Initializes all loaded addons during the common init phase.
     * @param registry the addon registry for item/block registration
     */
    public static void initAddons(AddonRegistry registry) {
        for (Addon addon : LOADED_ADDONS) {
            try {
                LOGGER.debug("Initializing addon: {}", addon.getAddonId());
                addon.onRegisterContent(registry);
            } catch (Exception e) {
                LOGGER.error("Error initializing addon: {}", addon.getAddonId(), e);
            }
        }
    }
    
    /**
     * Initializes client addon features (client init phase only).
     * @param clientRegistry the client-side addon registry
     */
    public static void initClientAddons(AddonClientRegistry clientRegistry) {
        if (Platform.getEnv() != EnvType.CLIENT) {
            return;
        }
        
        for (Addon addon : LOADED_ADDONS) {
            try {
                LOGGER.debug("Initializing client features for addon: {}", addon.getAddonId());
                addon.onClientInit(clientRegistry);
            } catch (Exception e) {
                LOGGER.error("Error initializing client features for addon: {}", addon.getAddonId(), e);
            }
        }
    }
    
    /**
     * Initializes server-related addon features (server startup phase only).
     * @param registry the server-side addon registry
     */
    public static void initServerAddons(AddonRegistry registry) {
        if (Platform.getEnv() == EnvType.CLIENT) {
            return;
        }
        
        for (Addon addon : LOADED_ADDONS) {
            try {
                LOGGER.debug("Initializing server features for addon: {}", addon.getAddonId());
                addon.onServerStart(registry);
            } catch (Exception e) {
                LOGGER.error("Error initializing server features for addon: {}", addon.getAddonId(), e);
            }
        }
    }
    
    /**
     * Gets all loaded addons.
     * @return unmodifiable list of loaded addons
     */
    public static List<Addon> getLoadedAddons() {
        return Collections.unmodifiableList(LOADED_ADDONS);
    }
    
    /**
     * Gets a specific addon by ID.
     * @param addonId the addon ID
     * @return the addon, or empty if not found
     */
    public static Optional<Addon> getAddon(String addonId) {
        return Optional.ofNullable(ADDON_MAP.get(addonId));
    }
    
    /**
     * Checks if an addon is loaded.
     * @param addonId the addon ID
     * @return true if the addon is loaded
     */
    public static boolean isAddonLoaded(String addonId) {
        return ADDON_MAP.containsKey(addonId);
    }
}
