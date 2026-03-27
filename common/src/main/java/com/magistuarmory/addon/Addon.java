package com.magistuarmory.addon;

/**
 * Base interface for Epic Knights addons.
 * 
 * Addons must implement this interface and register themselves via Java's ServiceLoader mechanism.
 * Add an entry to `META-INF/services/com.magistuarmory.addon.Addon` in your addon JAR.
 */
public interface Addon {
    
    /**
     * Returns the unique identifier for this addon.
     * Should be in the format "namespace:addon_name" (e.g., "mymod:my_addon").
     * @return the addon ID
     */
    String getAddonId();
    
    /**
     * Returns the human-readable name of this addon.
     * @return the addon name
     */
    String getAddonName();
    
    /**
     * Returns the version of this addon.
     * @return the addon version
     */
    String getAddonVersion();
    
    /**
     * Called during common initialization phase.
     * Register items, blocks, recipes, etc. here.
     * @param registry the addon registry for items and blocks
     */
    void onRegisterContent(AddonRegistry registry);
    
    /**
     * Called during client initialization phase (runs on client only).
     * Register models, renderers, etc. here.
     * @param clientRegistry the client-side addon registry
     */
    void onClientInit(AddonClientRegistry clientRegistry);
    
    /**
     * Called during server startup (runs on server only).
     * Set up game logic, datapack listeners, etc. here.
     * @param registry the server-side addon registry
     */
    void onServerStart(AddonRegistry registry);
}
