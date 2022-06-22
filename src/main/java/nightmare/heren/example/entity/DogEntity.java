package nightmare.heren.example.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

public class DogEntity extends SlimeEntity {

    public DogEntity(EntityType<? extends SlimeEntity> entityType,World world) {
        super(entityType, world);
        experiencePoints=250;
    }
    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return ZombieEntity.createMobAttributes();
    }
}