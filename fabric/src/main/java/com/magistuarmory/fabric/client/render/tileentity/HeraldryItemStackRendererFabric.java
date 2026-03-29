package com.magistuarmory.fabric.client.render.tileentity;

import com.magistuarmory.client.render.tileentity.HeraldryItemStackRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class HeraldryItemStackRendererFabric extends HeraldryItemStackRenderer
{
	public HeraldryItemStackRendererFabric(String id, ResourceLocation location)
	{
		super(id, location);
	}
}