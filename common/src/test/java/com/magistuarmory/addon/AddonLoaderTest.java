package com.magistuarmory.addon;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Unit tests for the AddonLoader class.
 */
public class AddonLoaderTest {
    
    @Before
    public void setUp() {
        // Clear loaded addons before each test if needed
    }
    
    @Test
    public void testGetLoadedAddons_IsUnmodifiable() {
        List<Addon> addons = AddonLoader.getLoadedAddons();
        assertNotNull("Loaded addons list should not be null", addons);
        
        // Verify the list is unmodifiable
        try {
            addons.add(new MockAddon());
            fail("Should not be able to modify the addons list");
        } catch (UnsupportedOperationException e) {
            // Expected behavior
        }
    }
    
    @Test
    public void testGetAddon_ReturnsEmpty_ForNonExistentAddon() {
        Optional<Addon> addon = AddonLoader.getAddon("nonexistent:addon");
        assertFalse("Should return empty Optional for non-existent addon", addon.isPresent());
    }
    
    @Test
    public void testIsAddonLoaded_ReturnsFalse_ForNonExistentAddon() {
        boolean loaded = AddonLoader.isAddonLoaded("nonexistent:addon");
        assertFalse("Should return false for non-existent addon", loaded);
    }
    
    /**
     * Mock addon for testing purposes.
     */
    private static class MockAddon implements Addon {
        @Override
        public String getAddonId() {
            return "test:mock";
        }
        
        @Override
        public String getAddonName() {
            return "Mock Addon";
        }
        
        @Override
        public String getAddonVersion() {
            return "1.0.0";
        }
        
        @Override
        public void onRegisterContent(AddonRegistry registry) {
            // Mock implementation
        }
        
        @Override
        public void onClientInit(AddonClientRegistry clientRegistry) {
            // Mock implementation
        }
        
        @Override
        public void onServerStart(AddonRegistry registry) {
            // Mock implementation
        }
    }
}
