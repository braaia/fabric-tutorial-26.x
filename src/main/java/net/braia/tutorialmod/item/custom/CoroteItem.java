package net.braia.tutorialmod.item.custom;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CoroteItem extends Item {
    private static final List<Holder<MobEffect>> EFFECTS = List.of(
            MobEffects.HASTE,
            MobEffects.STRENGTH,
            MobEffects.REGENERATION,
            MobEffects.SPEED,
            MobEffects.SLOWNESS,
            MobEffects.MINING_FATIGUE,
            MobEffects.DARKNESS,
            MobEffects.NAUSEA
    );

    public CoroteItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NonNull ItemStack finishUsingItem(@NonNull ItemStack itemStack, Level level, @NonNull LivingEntity entity) {
        if (!level.isClientSide()) {
            Holder<MobEffect> effect = EFFECTS.get(ThreadLocalRandom.current().nextInt(EFFECTS.size()));
            entity.addEffect(new MobEffectInstance(effect, 300));
        }

        return super.finishUsingItem(itemStack, level, entity);
    }
}
