package nightmare.heren.example.enchants;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.entity.Entity;
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
        super(Rarity.COMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }
    @Override
    public int getMinPower(int level) {
        return level*60;
    }

    /**
     * 好的通铃术必须经过一次次的练习
     * @return 还可以再大些（可怜那些受害者）
     */
    @Override
    public int getMaxLevel() {
        return 10;
    }

    /**
     * 口区
     * @param user 狗东西
     * @param target 烈士
     * @param level 法力等级
     */
    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity living) {
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 20 * 5 * level, level));
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 20 * 5 * level, 1));
        }
        user.heal(3);
        super.onTargetDamaged(user, target, level);
    }
}
