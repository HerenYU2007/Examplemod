package nightmare.heren.example.enchants;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

/**
 * 它有一个帅气的名字————WithTina(通铃术)
 * 据说xyj因为在凡人面前擅自使用通铃术，被罚在mc里当一只会飞会喷shi的dog
 * 而这个附魔的作者，因为在写程序时被通铃术偷袭，至今下落不明
 */
public class WithTina extends Enchantment {
    public WithTina() {
        super(Rarity.COMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 1 + level * 60;
    }

    /**
     * 好的通铃术必须经过一次次的练习
     *
     * @return 还可以再大些（可怜那些受害者）
     */
    @Override
    public int getMaxLevel() {
        return 10;
    }

    /**
     * 口区
     *
     * @param user   狗东西
     * @param target 烈士
     * @param level  法力等级
     */
    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity living) {
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 20 * 5 * level, level));
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 20 * 5 * level, 1));
        }
        user.heal(4);
        super.onTargetDamaged(user, target, level);
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        if (attacker instanceof LivingEntity living) {
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 3 * level, 2));
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 20 * 3 * level, 1));
        }
        super.onUserDamaged(user, attacker, level);
    }

    /**
     * 好疼
     *
     * @param level
     * @param group
     * @return
     */
    @Override
    public float getAttackDamage(int level, EntityGroup group) {
        return level * 1.5f;
    }

}