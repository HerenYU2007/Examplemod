package nightmare.heren.example.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

/*
 * 我们创建的实体继承自 PathAwareEntity, 它继承自 MobEntity, 而 MobEntity 继承自 LivingEntity.
 *
 * LivingEntity 拥有生命值，并且可以造成伤害。
 * MobEntity 具有AI逻辑和移动控制。
 * PathAwareEntity 提供额外的寻路系统，很多AI任务都需要用到寻路。
 */
public class DogEntity extends SlimeEntity {

    public DogEntity(EntityType<? extends SlimeEntity> entityType,World world) {
        super(entityType, world);
        experiencePoints=250;
    }
    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return SlimeEntity.createMobAttributes();
    }
}