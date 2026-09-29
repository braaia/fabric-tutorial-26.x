package net.braia.tutorialmod.item.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.chicken.Chicken;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class KatanaItem extends Item {
    public KatanaItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NonNull InteractionResult interactLivingEntity(@NonNull ItemStack itemStack, @NonNull Player player, @NonNull LivingEntity target, @NonNull InteractionHand type) {
        Level level = target.level();
        if (level.isClientSide()) {
            return InteractionResult.PASS;
        }

        Chicken newChicken = new Chicken(EntityType.CHICKEN, target.level());

        newChicken.setPos(
                target.getX(),
                target.getY(),
                target.getZ()
        );

        target.discard();

        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                    ParticleTypes.GLOW,
                    newChicken.getX() + 0.5,
                    newChicken.getY() + 1,
                    newChicken.getZ() + 0.5,
                    12,     // quantidade
                    0.25,   // espalhamento em X
                    0.25,   // espalhamento em Y
                    0.25,   // espalhamento em Z
                    0.02    // velocidade
            );
        }
        level.playSound(null, newChicken.getOnPos(), SoundEvents.WITHER_HURT, SoundSource.BLOCKS, 2f, 1f);
        target.level().addFreshEntity(newChicken);

        return InteractionResult.SUCCESS;
    }
}
