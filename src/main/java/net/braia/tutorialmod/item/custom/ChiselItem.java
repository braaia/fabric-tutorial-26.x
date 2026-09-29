package net.braia.tutorialmod.item.custom;

import net.braia.tutorialmod.block.ModBlocks;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.NonNull;

import java.util.Map;

public class ChiselItem extends Item {
    public ChiselItem(Properties properties) {
        super(properties);
    }

    private static final Map<Block, Block> CHISEL_MAP =
            Map.of(
                    Blocks.AMETHYST_BLOCK, ModBlocks.FLUORITE_BLOCK,
                    Blocks.EMERALD_ORE, ModBlocks.FLUORITE_ORE,
                    Blocks.RAW_IRON_BLOCK, ModBlocks.RAW_FLUORITE_BLOCK,
                    Blocks.DEEPSLATE_EMERALD_ORE, ModBlocks.FLUORITE_DEEPSLATE_ORE,
                    Blocks.NETHER_GOLD_ORE, ModBlocks.FLUORITE_NETHER_ORE,
                    Blocks.END_STONE, ModBlocks.FLUORITE_END_ORE
            );

    @Override
    public @NonNull InteractionResult useOn(@NonNull UseOnContext context) {
        // Right Click Block
        // Change Block from A to B...
        Level level = context.getLevel();
        if (level.isClientSide()) {
            return InteractionResult.PASS;
        }

        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if (CHISEL_MAP.containsKey(clickedBlock)) {
            level.setBlockAndUpdate(context.getClickedPos(), CHISEL_MAP.get(clickedBlock).defaultBlockState());

            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                        ParticleTypes.GLOW,
                        context.getClickedPos().getX() + 0.5,
                        context.getClickedPos().getY() + 1,
                        context.getClickedPos().getZ() + 0.5,
                        12,     // quantidade
                        0.25,   // espalhamento em X
                        0.25,   // espalhamento em Y
                        0.25,   // espalhamento em Z
                        0.02    // velocidade
                );
            }
            level.playSound(null, context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS, 2f, 1f);

            context.getItemInHand().hurtAndBreak(1, context.getPlayer(), context.getHand());
        }

        return InteractionResult.SUCCESS;
    }
}
