package nightmare.heren.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import nightmare.heren.example.block.ModBlocks;
import nightmare.heren.example.enchants.WithTina;
import nightmare.heren.example.entity.DogEntity;
import nightmare.heren.example.item.ModItems;

import java.util.Arrays;


public class ExampleMod implements ModInitializer {

	public final static String MOD_ID = "example";
	public static final ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "example"),
			() -> new ItemStack(ModItems.MODICON));
	public static final EntityType<DogEntity> DOG = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier(ExampleMod.MOD_ID, "dog"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DogEntity::new).dimensions(EntityDimensions.fixed(0.75f, 2f)).build()
	);

	@Override
	public void onInitialize() {
		ModItems.ModItemRegister();
		ModBlocks.registerModBlocks();
		FabricDefaultAttributeRegistry.register(DOG, DogEntity.createDogAttributes());
		regEnchants();
		regEvents();

		ConfiguredFeature<?, ?> dogBlockFeature = new ConfiguredFeature
				(Feature.ORE, new OreFeatureConfig(
						OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
						ModBlocks.DOG_BLOCK.getDefaultState(), 9)); // vein size
		PlacedFeature dogPlacedFeature = new PlacedFeature(
				RegistryEntry.of(dogBlockFeature), Arrays.asList(
				CountPlacementModifier.of(20), // number of veins per chunk
				SquarePlacementModifier.of(), // spreading horizontally
				HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))
		)); // height
		Identifier dog_block = new Identifier(MOD_ID, "overworld_dog_block");
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, dog_block, dogBlockFeature);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, dog_block, dogPlacedFeature);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
				RegistryKey.of(Registry.PLACED_FEATURE_KEY, dog_block));
	}

	private void regEvents() {

	}

	private void regEnchants() {
		Tool.regEnchant("with_tina", new WithTina());
	}
}