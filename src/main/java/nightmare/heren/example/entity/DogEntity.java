package nightmare.heren.example.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import nightmare.heren.example.item.ModItems;

public class DogEntity extends PathAwareEntity {
    public DogEntity(EntityType<? extends DogEntity> entityType, World world) {
        super(entityType, world);
        experiencePoints = 250;
    }
    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.8));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal(this, HostileEntity.class, true));
        this.targetSelector.add(2, new RevengeGoal(this));
        this.goalSelector.add(4, new TemptGoal(this, 1.2, Ingredient.ofItems(ModItems.SHIT), false));
        this.goalSelector.add(4, new MeleeAttackGoal(this, 1.0, false));
    }
    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!itemStack.isOf(ModItems.SHIT)) {
            return ActionResult.PASS;
        }
        player.startRiding(this);
        return ActionResult.success(this.world.isClient);
    }
    //TODO drop a tina_block if is killed by an item with the enchantment 'WithTina'
/*
    @Override
    protected void dropEquipment(DamageSource source, int lootingMultiplier, boolean allowDrops) {
        super.dropEquipment(source, lootingMultiplier, allowDrops);
        Iterator<ItemStack> it=source.getAttacker().getItemsHand().iterator();
        if (source.getAttacker() != this && it.hasNext()&&it.next().getEnchantments().) {
            this.dropItem(ModBlocks.TINA_BLOCK);
        }
    }

 */

    public static DefaultAttributeContainer.Builder createDogAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE,25)//伤害
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.8)//速度
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 300);//生命值
    }
}