package com.magistuarmory.client.render.entity.layer;

import com.magistuarmory.EpicKnights;
import com.magistuarmory.client.render.model.ModModels;
import com.magistuarmory.client.render.model.decoration.ArmorDecorationModel;
import com.magistuarmory.client.render.model.decoration.ArmorDecorationModelSet;
import com.magistuarmory.client.render.model.decoration.SurcoatModel;
import com.magistuarmory.component.ModDataComponents;
import com.magistuarmory.item.ArmorDecoration;
import com.magistuarmory.item.ArmorDecorationItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatternLayers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.magistuarmory.item.ArmorDecorationItem.createDecorations;
import static com.magistuarmory.item.ArmorDecorationItem.getDecorationTags;


@Environment(EnvType.CLIENT)
public class ArmorDecorationLayer<T extends LivingEntity> implements ArmorPatternLayer
{

   private static final String ARMOR_DIR_PREFIX = "textures/models/armor/";
   private final ArmorDecorationModel coatModel;
   private final String coatDirPrefix;
   private final ResourceLocation coatTexture;
   private final ResourceLocation basePatternTexture;
   private final ArmorDecorationModelSet decorationModels;

   public ArmorDecorationLayer(ArmorDecorationModelSet decorationModels, EntityRendererProvider.Context context, ResourceLocation location)
   {
      this.decorationModels = decorationModels;
      this.coatModel = new SurcoatModel(context.bakeLayer(new ModelLayerLocation(location, "decoration")));
      this.coatDirPrefix = this.getDirPrefix(location);
      this.coatTexture = this.getTexture(location);
      this.basePatternTexture = ResourceLocation.fromNamespaceAndPath(EpicKnights.ID, coatDirPrefix + "base.png");
   }

   public ResourceLocation getTexture(ResourceLocation location)
   {
      return getTexture(location, false);
   }

   public ResourceLocation getTexture(ResourceLocation location, boolean overlay)
   {
      return ResourceLocation.fromNamespaceAndPath(location.getNamespace(), ARMOR_DIR_PREFIX + location.getPath() + (overlay ? "_overlay.png" : ".png"));
   }

   public String getDirPrefix(ResourceLocation location)
   {
      return ARMOR_DIR_PREFIX + location.getPath() + "/";
   }

   public void render(PoseStack pose, MultiBufferSource buffer, int p, Object state, float f, float f2, float f3, float f4, float f5, float f6)
   {
      // Rendering disabled for 1.21.4 compatibility
      throw new UnsupportedOperationException("Armor decoration rendering not yet updated for 1.21.4");
   }

   private void renderPiece(PoseStack pose, MultiBufferSource buffer, T entity, EquipmentSlot slot, int p)
   {
      ItemStack stack = entity.getItemBySlot(slot);
      if (stack.getItem() instanceof ArmorItem armoritem)
      {
         if (stack.get(ModDataComponents.ARMOR_DECORATION.get()) != null)
         {
            for (ArmorDecorationItem.DecorationInfo info : createDecorations(getDecorationTags(stack)))
            {
               ResourceLocation location = info.location();
               ArmorDecorationModel model = this.getArmorDecorationModel(location);
               if (model != null)
               {
                  // TODO: restore parent model copying once rendering is updated for 1.21.4
                  if (info.dyeable())
                  {
                     renderDecoration(pose, buffer, p, OverlayTexture.NO_OVERLAY, info.color(), stack.hasFoil(), model.parts(), getTexture(location));
                     renderDecoration(pose, buffer, p, OverlayTexture.NO_OVERLAY, stack.hasFoil(), model.parts(), getTexture(location, true));
                  }
                  else
                  {
                     renderDecoration(pose, buffer, p, OverlayTexture.NO_OVERLAY, stack.hasFoil(), model.parts(), getTexture(location));
                  }
               }
            }
         }
         BannerPatternLayers patterns = stack.get(DataComponents.BANNER_PATTERNS);
         if (slot == EquipmentSlot.CHEST && patterns != null)
         {
            DyeColor basecolor = stack.get(DataComponents.BASE_COLOR);
            // TODO: restore parent model copying once rendering is updated for 1.21.4
            renderDecoration(pose, buffer, p, OverlayTexture.NO_OVERLAY, stack.hasFoil(), this.coatModel.parts(), this.coatTexture);
            List<Pair<Holder<BannerPattern>, DyeColor>> list = patterns == null ? new ArrayList<>() : patterns.layers().stream().map(l -> Pair.of(l.pattern(), l.color())).collect(Collectors.toList());
            renderPatterns(pose, buffer, p, OverlayTexture.NO_OVERLAY, list, stack.hasFoil(), this.coatModel.parts(), basecolor);
         }
      }
   }

   public ArmorDecorationModel getCoatModel() {
      return this.coatModel;
   }

   public ArmorDecorationModel getArmorDecorationModel(ResourceLocation location)
   {
      return this.decorationModels.get(location);
   }

   public void renderDecoration(PoseStack pose, MultiBufferSource buffer, int p, int overlay, boolean hasfoil, ModelPart[] modelparts, ResourceLocation texture)
   {
      renderDecoration(pose, buffer, p, overlay, -1, hasfoil, modelparts, texture);
   }

   public void renderDecoration(PoseStack pose, MultiBufferSource buffer, int p, int overlay, int color, boolean hasfoil, ModelPart[] modelparts, ResourceLocation texture)
   {
      VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(buffer, RenderType.entityCutoutNoCull(texture), hasfoil);

      for (ModelPart part : modelparts)
         part.render(pose, vertexconsumer, p, overlay, color);
   }

   @Override
   public ResourceLocation getBaseTexture(boolean withPattern)
   {
      return this.coatTexture;
   }

   public ResourceLocation getBaseTexture()
   {
      return this.coatTexture;
   }

   @Override
   public ResourceLocation getBasePatternTexture()
   {
      return this.basePatternTexture;
   }

   @Override
   public ResourceLocation getPatternTexture(ResourceLocation patternlocation)
   {
      return ResourceLocation.fromNamespaceAndPath(this.coatTexture.getNamespace(), this.coatDirPrefix + patternlocation.getPath() + ".png");
   }

   public void registerDecorations(List<RegistrySupplier<? extends ArmorDecoration>> armorDecorationItems, EntityRendererProvider.Context context) {
      this.decorationModels.registerDecorations(armorDecorationItems, context);
   }
}
