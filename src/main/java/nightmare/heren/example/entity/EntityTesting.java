package nightmare.heren.example.entity;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;

public class EntityTesting implements ModInitializer {

    public static final EntityType<modEntity> CUBE = null;

    @Override
    public void onInitialize() {
        /*
         * 注册我们方块实体的默认属性。
         * 属性是一个生物当前状态的数值，其中有攻击伤害和生命值等。
         * 如果实体没有及时注册适当的属性，则游戏将崩溃。
         *
         * 在1.15中，它是通过重写实体类内部的方法来完成的。
         * 大部分的原版实体都有一个静态方法(例如,ZombieEntity#createZombieAttributes)用于初始化它们的属性。
         */
        FabricDefaultAttributeRegistry.register(CUBE, modEntity.createMobAttributes());
    }
}