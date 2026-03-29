package com.magistuarmory.client.render.fabric;

import com.magistuarmory.client.render.tileentity.HeraldryItemStackRenderer;
import com.magistuarmory.fabric.client.render.tileentity.HeraldryItemStackRendererFabric;
import com.magistuarmory.api.item.ModItemsProvider;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class ModRenderImpl
{
    public static void setupPlatform(ModItemsProvider content)
    {
        // No fabric-specific client platform initialization required for 1.21.4 port.
    }

    public static void registerModelsLoadListener(ModItemsProvider content)
    {
        // No custom model load listener needed for the port.
    }

    public static HeraldryItemStackRenderer createHeraldryItemStackRenderer(String id, ResourceLocation location)
    {
        return new HeraldryItemStackRendererFabric(id, location);
    }
}
