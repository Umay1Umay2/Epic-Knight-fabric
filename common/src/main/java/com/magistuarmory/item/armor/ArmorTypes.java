package com.magistuarmory.item.armor;

import com.magistuarmory.EpicKnights;
import com.magistuarmory.config.ArmorConfig;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;


public class ArmorTypes
{
	public static final ArmorConfig ARMOR_CONFIG = EpicKnights.CONFIG.armor;

	public static final ArmorType MINICROWN;
	public static final ArmorType CROWN;
	public static final ArmorType FLOWERCROWN;
	public static final ArmorType KNIGHT;
	public static final ArmorType ARMET;
	public static final ArmorType STECHHELM;
	public static final ArmorType JOUSTING;
	public static final ArmorType SALLET;
	public static final ArmorType GOTHIC;
	public static final ArmorType MAXIMILIAN_HELMET;
	public static final ArmorType MAXIMILIAN;
	public static final ArmorType CHAINMAIL;
	public static final ArmorType KETTLEHAT;
	public static final ArmorType PLATEMAIL;
	public static final ArmorType BARBUTE;
	public static final ArmorType HALFARMOR;
	public static final ArmorType GREATHELM;
	public static final ArmorType CRUSADER;
	public static final ArmorType BRIGANDINE;
	public static final ArmorType GAMBESON;
	public static final ArmorType CEREMONIAL_ARMET;
	public static final ArmorType CEREMONIAL;
	public static final ArmorType SHISHAK;
	public static final ArmorType NORMAN;
	public static final ArmorType RUSTED_BARBUTE;
	public static final ArmorType RUSTED_HALFARMOR;
	public static final ArmorType RUSTED_CHAINMAIL;
	public static final ArmorType RUSTED_KETTLEHAT;
	public static final ArmorType RUSTED_NORMAN;
	public static final ArmorType RUSTED_GREATHELM;
	public static final ArmorType RUSTED_CRUSADER;
	public static final ArmorType BASCINET;
	public static final ArmorType XIV_CENTURY_KNIGHT;
	public static final ArmorType WINGED_HUSSAR_CHESTPLATE;
	public static final ArmorType CUIRASSIER;
	public static final ArmorType KASTENBRUST;
	public static final ArmorType GRAND_BASCINET;
	public static final ArmorType LAMELLAR;

	static {
		MINICROWN = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "minicrown"), ResourceLocation.parse("magistuarmory:minicrown"), ARMOR_CONFIG.get("minicrown"), SoundEvents.ARMOR_EQUIP_GOLD, false, "c:ingots/gold");
		CROWN = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "crown"), ResourceLocation.parse("magistuarmory:crown"), ARMOR_CONFIG.get("crown"), SoundEvents.ARMOR_EQUIP_GOLD, true, "c:ingots/gold");
		FLOWERCROWN = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "flowercrown"), ResourceLocation.parse("magistuarmory:flowercrown"), ARMOR_CONFIG.get("flowercrown"), SoundEvents.ARMOR_EQUIP_LEATHER, true);
		KNIGHT = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "knight"), ResourceLocation.withDefaultNamespace("default"), ARMOR_CONFIG.get("knight"), SoundEvents.ARMOR_EQUIP_IRON, false, "c:ingots/steel");
		ARMET = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "armet"), ResourceLocation.parse("magistuarmory:armet"), ARMOR_CONFIG.get("armet"), SoundEvents.ARMOR_EQUIP_IRON, true, "c:ingots/steel");
		STECHHELM = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "stechhelm"), ResourceLocation.parse("magistuarmory:stechhelm"), ARMOR_CONFIG.get("stechhelm"), SoundEvents.ARMOR_EQUIP_IRON, true, "c:ingots/steel");
		JOUSTING = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "jousting"), ResourceLocation.withDefaultNamespace("default"), ARMOR_CONFIG.get("jousting"), SoundEvents.ARMOR_EQUIP_IRON, false, "c:ingots/steel");
		SALLET = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "sallet"), ResourceLocation.parse("magistuarmory:sallet"), ARMOR_CONFIG.get("sallet"), SoundEvents.ARMOR_EQUIP_IRON, false, "c:ingots/steel");
		GOTHIC = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "gothic"), ResourceLocation.withDefaultNamespace("default"), ARMOR_CONFIG.get("gothic"), SoundEvents.ARMOR_EQUIP_IRON, false, "c:ingots/steel");
		MAXIMILIAN_HELMET = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "maximilian_helmet"), ResourceLocation.parse("magistuarmory:maximilian_helmet"), ARMOR_CONFIG.get("maximilianHelmet"), SoundEvents.ARMOR_EQUIP_IRON, false, "c:ingots/steel");
		MAXIMILIAN = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "maximilian"), ResourceLocation.withDefaultNamespace("default"), ARMOR_CONFIG.get("maximilian"), SoundEvents.ARMOR_EQUIP_IRON, false, "c:ingots/steel");
		CHAINMAIL = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "chainmail"), ResourceLocation.parse("magistuarmory:conic_helmet"), ARMOR_CONFIG.get("chainmail"), SoundEvents.ARMOR_EQUIP_CHAIN, false, "c:ingots/steel");
		KETTLEHAT = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "kettlehat"), ResourceLocation.parse("magistuarmory:kettlehat"), ARMOR_CONFIG.get("kettlehat"), SoundEvents.ARMOR_EQUIP_CHAIN, false, "c:ingots/steel");
		PLATEMAIL = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "platemail"), ResourceLocation.withDefaultNamespace("default"), ARMOR_CONFIG.get("platemail"), SoundEvents.ARMOR_EQUIP_CHAIN, false, "c:ingots/steel");
		BARBUTE = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "barbute"), ResourceLocation.parse("magistuarmory:barbute"), ARMOR_CONFIG.get("barbute"), SoundEvents.ARMOR_EQUIP_IRON, false, "c:ingots/steel");
		HALFARMOR = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "halfarmor"), ResourceLocation.withDefaultNamespace("default"), ARMOR_CONFIG.get("halfarmor"), SoundEvents.ARMOR_EQUIP_IRON, false, "c:ingots/steel");
		GREATHELM = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "greathelm"), ResourceLocation.parse("magistuarmory:crusader"), ARMOR_CONFIG.get("crusader"), SoundEvents.ARMOR_EQUIP_CHAIN, true, "c:ingots/steel");
		CRUSADER = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "crusader"), ResourceLocation.withDefaultNamespace("default"), ARMOR_CONFIG.get("crusader"), SoundEvents.ARMOR_EQUIP_CHAIN, true, "c:ingots/steel");
		BRIGANDINE = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "brigandine"), ResourceLocation.withDefaultNamespace("default"), ARMOR_CONFIG.get("brigandine"), SoundEvents.ARMOR_EQUIP_LEATHER, true, "c:ingots/steel");
		GAMBESON = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "gambeson"), ResourceLocation.withDefaultNamespace("default"), ARMOR_CONFIG.get("gambeson"), SoundEvents.ARMOR_EQUIP_LEATHER, true);
		CEREMONIAL_ARMET = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "ceremonialarmet"), ResourceLocation.parse("magistuarmory:armet"), ARMOR_CONFIG.get("ceremonialArmet"), SoundEvents.ARMOR_EQUIP_IRON, false, "c:ingots/steel");
		CEREMONIAL = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "ceremonial"), ResourceLocation.withDefaultNamespace("default"), ARMOR_CONFIG.get("ceremonial"), SoundEvents.ARMOR_EQUIP_IRON, false, "c:ingots/steel");
		SHISHAK = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "shishak"), ResourceLocation.parse("magistuarmory:conic_helmet"), ARMOR_CONFIG.get("shishak"), SoundEvents.ARMOR_EQUIP_IRON, false, "c:ingots/steel");
		NORMAN = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "norman"), ResourceLocation.parse("magistuarmory:conic_helmet"), ARMOR_CONFIG.get("norman"), SoundEvents.ARMOR_EQUIP_IRON, true, "c:ingots/steel");
		RUSTED_BARBUTE = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "rustedbarbute"), ResourceLocation.parse("magistuarmory:barbute"), ARMOR_CONFIG.get("rustedBarbute"), SoundEvents.ARMOR_EQUIP_IRON, false, "c:ingots/steel");
		RUSTED_HALFARMOR = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "rustedhalfarmor"), ResourceLocation.withDefaultNamespace("default"), ARMOR_CONFIG.get("rustedHalfarmor"), SoundEvents.ARMOR_EQUIP_IRON, false, "c:ingots/steel");
		RUSTED_CHAINMAIL = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "rustedchainmail"), ResourceLocation.parse("magistuarmory:conic_helmet"), ARMOR_CONFIG.get("rustedChainmail"), SoundEvents.ARMOR_EQUIP_CHAIN, false, "c:ingots/steel");
		RUSTED_KETTLEHAT = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "rustedkettlehat"), ResourceLocation.parse("magistuarmory:kettlehat"), ARMOR_CONFIG.get("rustedKettlehat"), SoundEvents.ARMOR_EQUIP_CHAIN, false, "c:ingots/steel");
		RUSTED_NORMAN = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "rustednorman"), ResourceLocation.parse("magistuarmory:conic_helmet"), ARMOR_CONFIG.get("rustedNorman"), SoundEvents.ARMOR_EQUIP_CHAIN, false, "c:ingots/steel");
		RUSTED_GREATHELM = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "rustedgreathelm"), ResourceLocation.parse("magistuarmory:crusader"), ARMOR_CONFIG.get("rustedCrusader"), SoundEvents.ARMOR_EQUIP_CHAIN, false, "c:ingots/steel");
		RUSTED_CRUSADER = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "rustedcrusader"), ResourceLocation.withDefaultNamespace("default"), ARMOR_CONFIG.get("rustedCrusader"), SoundEvents.ARMOR_EQUIP_CHAIN, false, "c:ingots/steel");
		BASCINET = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "bascinet"), ResourceLocation.parse("magistuarmory:bascinet"), ARMOR_CONFIG.get("bascinet"), SoundEvents.ARMOR_EQUIP_IRON, false, "c:ingots/steel");
		XIV_CENTURY_KNIGHT = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "xivcenturyknight"), ResourceLocation.withDefaultNamespace("default"), ARMOR_CONFIG.get("xivCenturyKnight"), SoundEvents.ARMOR_EQUIP_IRON, false, "c:ingots/steel");
		WINGED_HUSSAR_CHESTPLATE = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "wingedhussarchestplate"), ResourceLocation.parse("magistuarmory:wingedhussarchestplate"), ARMOR_CONFIG.get("wingedHussarChestplate"), SoundEvents.ARMOR_EQUIP_IRON, false, "c:ingots/steel");
		CUIRASSIER = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "cuirassier"), ResourceLocation.withDefaultNamespace("default"), ARMOR_CONFIG.get("cuirassier"), SoundEvents.ARMOR_EQUIP_IRON, true, "c:ingots/steel");
		KASTENBRUST = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "kastenbrust"), ResourceLocation.withDefaultNamespace("default"), ARMOR_CONFIG.get("kastenbrust"), SoundEvents.ARMOR_EQUIP_IRON, false, "c:ingots/steel");
		GRAND_BASCINET = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "grand_bascinet"), ResourceLocation.parse("magistuarmory:grand_bascinet"), ARMOR_CONFIG.get("grandBascinet"), SoundEvents.ARMOR_EQUIP_IRON, false, "c:ingots/steel");
		LAMELLAR = create(ResourceLocation.fromNamespaceAndPath("magistuarmory", "lamellar"), ResourceLocation.withDefaultNamespace("default"), ARMOR_CONFIG.get("lamellar"), SoundEvents.ARMOR_EQUIP_CHAIN, false, "c:ingots/steel");
	}



private static ArmorType create(ResourceLocation location,
				ResourceLocation modelLocation,
				ArmorConfig.ArmorTypeConfig config,
				Holder<SoundEvent> equipSound,
				boolean dyeable,
				String repairItemTag) {
		return new ArmorType(location, modelLocation, config.toughness, config.knockbackResistance, new Integer[] {config.bootsDurability, config.leggingsDurability, config.chestplateDurability, config.helmetDurability}, new Integer[] {config.bootsDefense, config.leggingsDefense, config.chestplateDefense, config.helmetDefense}, config.enchantmentValue, equipSound, config.enabled, dyeable, repairItemTag);
	}

	private static ArmorType create(ResourceLocation location,
				ResourceLocation modelLocation,
				ArmorConfig.ArmorTypeConfig config,
				Holder<SoundEvent> equipSound,
				boolean dyeable) {
		return new ArmorType(location, modelLocation, config.toughness, config.knockbackResistance, new Integer[] {config.bootsDurability, config.leggingsDurability, config.chestplateDurability, config.helmetDurability}, new Integer[] {config.bootsDefense, config.leggingsDefense, config.chestplateDefense, config.helmetDefense}, config.enchantmentValue, equipSound, config.enabled, dyeable);
	}

	public static void init() {
		// No runtime registration required for 1.21.4 armor material loading.
	}
}
