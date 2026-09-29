package net.braia.tutorialmod.creativemodetab;

import net.braia.tutorialmod.TutorialMod;
import net.braia.tutorialmod.block.ModBlocks;
import net.braia.tutorialmod.item.ModItems;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {
    private ModCreativeModeTabs() {
    }

    public static final CreativeModeTab TUTORIAL_ITEMS_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "tutorial_items"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.FLUORITE))
                    .title(Component.translatable("creativemodetab.tutorialmod.tutorial_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.FLUORITE);
                        output.accept(ModItems.RAW_FLUORITE);

                        output.accept(ModItems.CHISEL);
                        output.accept(ModItems.KATANA);
                    }).build());

    public static final CreativeModeTab TUTORIAL_BLOCKS_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "tutorial_blocks"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.FLUORITE_BLOCK))
                    .title(Component.translatable("creativemodetab.tutorialmod.tutorial_blocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.MAGIC_BLOCK);
                        output.accept(ModBlocks.FLUORITE_BLOCK);
                        output.accept(ModBlocks.RAW_FLUORITE_BLOCK);

                        output.accept(ModBlocks.FLUORITE_ORE);
                        output.accept(ModBlocks.FLUORITE_DEEPSLATE_ORE);
                        output.accept(ModBlocks.FLUORITE_END_ORE);
                        output.accept(ModBlocks.FLUORITE_NETHER_ORE);
                    }).build());

    public static void registerModCreativeModeTabs() {
    }
}
