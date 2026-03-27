package com.magistuarmory.addon;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the Addon interface and its contracts.
 */
public class AddonInterfaceTest {
    
    @Test
    public void testAddonMustImplementAllMethods() {
        Addon addon = new TestAddon();
        
        assertNotNull("getAddonId should return a value", addon.getAddonId());
        assertFalse("getAddonId should not return empty string", addon.getAddonId().isEmpty());
        
        assertNotNull("getAddonName should return a value", addon.getAddonName());
        assertFalse("getAddonName should not return empty string", addon.getAddonName().isEmpty());
        
        assertNotNull("getAddonVersion should return a value", addon.getAddonVersion());
        assertFalse("getAddonVersion should not return empty string", addon.getAddonVersion().isEmpty());
    }
    
    @Test
    public void testAddonIdFormat() {
        Addon addon = new TestAddon();
        String addonId = addon.getAddonId();
        
        // Addon ID should be in format "namespace:name"
        assertTrue("Addon ID should contain a colon", addonId.contains(":"));
        String[] parts = addonId.split(":");
        assertEquals("Addon ID should have exactly 2 parts", 2, parts.length);
        assertFalse("Namespace should not be empty", parts[0].isEmpty());
        assertFalse("Name should not be empty", parts[1].isEmpty());
    }
    
    @Test
    public void testAddonLifecycleMethods_CanBeCalled() {
        Addon addon = new TestAddon();
        
        // These should not throw exceptions
        addon.onRegisterContent(null);
        addon.onClientInit(null);
        addon.onServerStart(null);
    }
    
    /**
     * Test implementation of Addon for testing purposes.
     */
    private static class TestAddon implements Addon {
        @Override
        public String getAddonId() {
            return "test:example";
        }
        
        @Override
        public String getAddonName() {
            return "Test Addon";
        }
        
        @Override
        public String getAddonVersion() {
            return "1.0.0";
        }
        
        @Override
        public void onRegisterContent(AddonRegistry registry) {
            // No-op for testing
        }
        
        @Override
        public void onClientInit(AddonClientRegistry clientRegistry) {
            // No-op for testing
        }
        
        @Override
        public void onServerStart(AddonRegistry registry) {
            // No-op for testing
        }
    }
}
