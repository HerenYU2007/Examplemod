package nightmare.heren.example.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

/*
 * 我们创建的实体继承自 PathAwareEntity, 它继承自 MobEntity, 而 MobEntity 继承自 LivingEntity.
 *
 * LivingEntity 拥有生命值，并且可以造成伤害。
 * MobEntity 具有AI逻辑和移动控制。
 * PathAwareEntity 提供额外的寻路系统，很多AI任务都需要用到寻路。
 */
public class DogEntity extends PathAwareEntity {

    public DogEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }
    public static DefaultAttributeContainer.Builder createMobAttributes() {
        DefaultAttributeContainer.Builder builder= MobEntity.createMobAttributes();
        //后期添加自己的属性
        return builder;
    }
}