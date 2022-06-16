package nightmare.heren.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.ActionResult;
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
import nightmare.heren.example.block.modBlocks;
import nightmare.heren.example.entity.DogEntity;
import nightmare.heren.example.item.modItems;

import java.util.Arrays;


public class ExampleMod implements ModInitializer {

	public final static String MOD_ID = "example";

	@Override
	public void onInitialize(){
		modItems.ModItemRegister();
		modBlocks.registerModBlocks();
		/*
		 * 注册我们方块实体的默认属性。
		 * 属性是一个生物当前状态的数值，其中有攻击伤害和生命值等。
		 * 如果实体没有及时注册适当的属性，则游戏将崩溃。
		 *
		 * 在1.15中，它是通过重写实体类内部的方法来完成的。
		 * 大部分的原版实体都有一个静态方法(例如,ZombieEntity#createZombieAttributes)用于初始化它们的属性。
		 */
		FabricDefaultAttributeRegistry.register(DOG, DogEntity.createMobAttributes());

		AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			if (entity instanceof DogEntity dog){
				dog.setAttacking(player);
			}
			return ActionResult.PASS;
		});
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
				new Identifier("example", "overworld_dog_block"), OVERWORLD_DOG_BLOCK_CONFIGURED_FEATURE);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("example", "overworld_dog_block"),
				OVERWORLD_DOG_BLOCK_PLACED_FEATURE);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
				RegistryKey.of(Registry.PLACED_FEATURE_KEY,
						new Identifier("example", "overworld_dog_block")));
	}
	public static final EntityType<DogEntity> DOG = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier(ExampleMod.MOD_ID, "dog"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DogEntity::new).dimensions(EntityDimensions.fixed(0.75f, 2f)).build()
	);


	private static ConfiguredFeature<?, ?> OVERWORLD_DOG_BLOCK_CONFIGURED_FEATURE = new ConfiguredFeature
			(Feature.ORE, new OreFeatureConfig(
					OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
					Blocks.NETHERITE_BLOCK.getDefaultState(),
					9)); // vein size

	public static PlacedFeature OVERWORLD_DOG_BLOCK_PLACED_FEATURE = new PlacedFeature(
			RegistryEntry.of(OVERWORLD_DOG_BLOCK_CONFIGURED_FEATURE),
			Arrays.asList(
					CountPlacementModifier.of(20), // number of veins per chunk
					SquarePlacementModifier.of(), // spreading horizontally
					HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))
			)); // height

	}



