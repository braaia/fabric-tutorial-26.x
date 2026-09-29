package net.braia.tutorialmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.NonNull;

public class MagicBlock extends Block {
    public MagicBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NonNull InteractionResult useWithoutItem(@NonNull BlockState state, Level level, @NonNull BlockPos pos, @NonNull Player player, @NonNull BlockHitResult hitResult) {
        if (level.isClientSide()) {
            return InteractionResult.PASS;
        }

        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                    ParticleTypes.GLOW,
                    pos.getX() + 0.5,
                    pos.getY() + 1,
                    pos.getZ() + 0.5,
                    12,     // quantidade
                    0.25,   // espalhamento em X
                    0.25,   // espalhamento em Y
                    0.25,   // espalhamento em Z
                    0.02    // velocidade
            );
        }
        level.playSound(null, pos, SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS, 2f, 1f);

        return InteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(@NonNull Level level, @NonNull BlockPos pos, @NonNull BlockState onState, @NonNull Entity entity) {
        if (entity instanceof Player player) {
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 300));
        }

        super.stepOn(level, pos, onState, entity);
    }
}
