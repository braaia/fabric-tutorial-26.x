package net.braia.tutorialmod.item.custom;

import net.braia.tutorialmod.block.ModBlocks;
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
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if (CHISEL_MAP.containsKey(clickedBlock) && !level.isClientSide()) {
            level.setBlockAndUpdate(context.getClickedPos(), CHISEL_MAP.get(clickedBlock).defaultBlockState());

            context.getItemInHand().hurtAndBreak(1, context.getPlayer(), context.getHand());
        }

        return InteractionResult.SUCCESS;
    }
}
