package com.magistuarmory.addon;

import net.minecraft.client.model.geom.ModelLayerLocation;

/**
 * Client-side registry for addon content.
 * Use this to register models, renderers, and other client-only content.
 */
public interface AddonClientRegistry {
    
    /**
     * Registers a custom item model layer.
     * @param location the model layer location
     * @param layerName the layer name
     * @return the registered model layer location
     */
    ModelLayerLocation registerModelLayer(String layerName);
    
    /**
     * Gets the texture location for addon content.
     * @param textureName the texture name (without namespace)
     * @return a string that can be used for texture paths
     */
    default String getTexturePath(String textureName) {
        return "textures/addon/" + textureName + ".png";
    }
    
    /**
     * Gets the model location for addon content.
     * @param modelName the model name
     * @return a string that can be used for model paths
     */
    default String getModelPath(String modelName) {
        return "models/addon/" + modelName + ".json";
    }
}
