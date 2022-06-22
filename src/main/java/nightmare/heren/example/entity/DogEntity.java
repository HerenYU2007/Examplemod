package nightmare.heren.example.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import nightmare.heren.example.item.ModItems;

import java.util.EnumSet;

public class DogEntity extends SlimeEntity {

    public DogEntity(EntityType<? extends SlimeEntity> entityType, World world) {
        super(entityType, world);
        experiencePoints = 250;
        this.moveControl = new DogMoveControl(this);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimmingGoal(this));
        this.goalSelector.add(2, new FaceTowardTargetGoal(this));
        this.goalSelector.add(3, new RandomLookGoal(this));
        this.goalSelector.add(5, new MoveGoal(this));
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
    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return SlimeEntity.createMobAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE,100).add(EntityAttributes.GENERIC_MAX_HEALTH, 200.0);
    }

    static class DogMoveControl
            extends MoveControl {
        private float targetYaw;
        private int ticksUntilJump;
        private final DogEntity dog;
        private boolean jumpOften;

        public DogMoveControl(DogEntity dog) {
            super(dog);
            this.dog = dog;
            this.targetYaw = 180.0f * dog.getYaw() / (float) Math.PI;
        }

        public void look(float targetYaw, boolean jumpOften) {
            this.targetYaw = targetYaw;
            this.jumpOften = jumpOften;
        }

        public void move(double speed) {
            this.speed = speed*20;
            this.state = MoveControl.State.MOVE_TO;
        }

        @Override
        public void tick() {
            this.entity.setYaw(this.wrapDegrees(this.entity.getYaw(), this.targetYaw, 90.0f));
            this.entity.headYaw = this.entity.getYaw();
            this.entity.bodyYaw = this.entity.getYaw();
            if (this.state != MoveControl.State.MOVE_TO) {
                this.entity.setForwardSpeed(0.0f);
                return;
            }
            this.state = MoveControl.State.WAIT;
            if (this.entity.isOnGround()) {
                this.entity.setMovementSpeed((float) (this.speed * this.entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
                if (this.ticksUntilJump-- <= 0) {
                    this.ticksUntilJump = this.dog.getTicksUntilNextJump();
                    if (this.jumpOften) {
                        this.ticksUntilJump /= 3;
                    }
                    this.dog.getJumpControl().setActive();
                } else {
                    this.dog.sidewaysSpeed = 0.0f;
                    this.dog.forwardSpeed = 0.0f;
                    this.entity.setMovementSpeed(0.0f);
                }
            } else {
                this.entity.setMovementSpeed((float) (this.speed * this.entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
            }
        }
    }

    static class SwimmingGoal
            extends Goal {
        private final DogEntity dog;

        public SwimmingGoal(DogEntity dog) {
            this.dog = dog;
            this.setControls(EnumSet.of(Goal.Control.JUMP, Goal.Control.MOVE));
            dog.getNavigation().setCanSwim(true);
        }

        @Override
        public boolean canStart() {
            return (this.dog.isTouchingWater() || this.dog.isInLava()) && this.dog.getMoveControl() instanceof DogMoveControl;
        }

        @Override
        public boolean shouldRunEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            if (this.dog.getRandom().nextFloat() < 0.8f) {
                this.dog.getJumpControl().setActive();
            }
            ((DogMoveControl) this.dog.getMoveControl()).move(1.2);
        }
    }

    static class FaceTowardTargetGoal
            extends Goal {
        private final DogEntity dog;
        private int ticksLeft;

        public FaceTowardTargetGoal(DogEntity dog) {
            this.dog = dog;
            this.setControls(EnumSet.of(Goal.Control.LOOK));
        }

        @Override
        public boolean canStart() {
            LivingEntity livingEntity = this.dog.getTarget();
            if (livingEntity == null) {
                return false;
            }
            if (!this.dog.canTarget(livingEntity)) {
                return false;
            }
            return this.dog.getMoveControl() instanceof DogMoveControl;
        }

        @Override
        public void start() {
            this.ticksLeft = FaceTowardTargetGoal.toGoalTicks(300);
            super.start();
        }

        @Override
        public boolean shouldContinue() {
            LivingEntity livingEntity = this.dog.getTarget();
            if (livingEntity == null) {
                return false;
            }
            if (!this.dog.canTarget(livingEntity)) {
                return false;
            }
            return --this.ticksLeft > 0;
        }

        @Override
        public boolean shouldRunEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            LivingEntity livingEntity = this.dog.getTarget();
            if (livingEntity != null) {
                this.dog.lookAtEntity(livingEntity, 10.0f, 10.0f);
            }
            ((DogMoveControl) this.dog.getMoveControl()).look(this.dog.getYaw(), this.dog.canAttack());
        }
    }

    static class RandomLookGoal
            extends Goal {
        private final DogEntity dog;
        private float targetYaw;
        private int timer;

        public RandomLookGoal(DogEntity dog) {
            this.dog = dog;
            this.setControls(EnumSet.of(Goal.Control.LOOK));
        }

        @Override
        public boolean canStart() {
            return this.dog.getTarget() == null && (this.dog.isOnGround() || this.dog.isTouchingWater() || this.dog.isInLava() || this.dog.hasStatusEffect(StatusEffects.LEVITATION)) && this.dog.getMoveControl() instanceof DogMoveControl;
        }

        @Override
        public void tick() {
            if (--this.timer <= 0) {
                this.timer = this.getTickCount(40 + this.dog.getRandom().nextInt(60));
                this.targetYaw = this.dog.getRandom().nextInt(360);
            }
            ((DogMoveControl) this.dog.getMoveControl()).look(this.targetYaw, false);
        }
    }

    static class MoveGoal
            extends Goal {
        private final DogEntity dog;

        public MoveGoal(DogEntity dog) {
            this.dog = dog;
            this.setControls(EnumSet.of(Goal.Control.JUMP, Goal.Control.MOVE));
        }

        @Override
        public boolean canStart() {
            return !this.dog.hasVehicle();
        }

        @Override
        public void tick() {
            ((DogMoveControl) this.dog.getMoveControl()).move(1.0);
        }
    }
}