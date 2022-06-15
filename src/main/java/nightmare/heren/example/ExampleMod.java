package nightmare.heren.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nightmare.heren.example.block.modBlocks;
import nightmare.heren.example.entity.DogEntity;
import nightmare.heren.example.item.modItems;

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
	}
	public static final EntityType<DogEntity> DOG = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier(ExampleMod.MOD_ID, "dog"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DogEntity::new).dimensions(EntityDimensions.fixed(0.75f, 2f)).build()
	);
}

