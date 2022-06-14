package nightmare.heren.example.entity;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityTesting implements ModInitializer {

    /*
     * 使用 "entitytesting:cube" 作为ID注册我们的实体
     *
     * 这个实体注册在了 SpawnGroup#CREATURE 类别下，大多数的动物和友好或中立的生物都注册在这个类别下。
     * 它有一个 .75 * .75 (或12个像素宽，即一个方块的3/4)大小的碰撞体积。
     */
    public static final EntityType<CubeEntity> CUBE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("entitytesting", "cube"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CubeEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );

    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(CUBE, CubeEntity.createMobAttributes());

    }
}