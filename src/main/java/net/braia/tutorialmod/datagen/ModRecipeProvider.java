package net.braia.tutorialmod.datagen;

import net.braia.tutorialmod.block.ModBlocks;
import net.braia.tutorialmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                final String FLUORITE_GROUP = "fluorite";

                List<ItemLike> FLUORITE_SMELTABLES = List.of(ModItems.RAW_FLUORITE, ModBlocks.FLUORITE_ORE,
                        ModBlocks.FLUORITE_DEEPSLATE_ORE, ModBlocks.FLUORITE_END_ORE, ModBlocks.FLUORITE_NETHER_ORE);

                oreSmelting(FLUORITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.FLUORITE, 0.25f, 200, FLUORITE_GROUP);
                oreBlasting(FLUORITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.FLUORITE, 0.25f, 100, FLUORITE_GROUP);

                nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.FLUORITE, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FLUORITE_BLOCK);
                nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.RAW_FLUORITE, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_FLUORITE_BLOCK);

                shaped(RecipeCategory.MISC, Items.DIAMOND_SWORD)
                        .define('D', Items.DIAMOND)
                        .define('F', ModItems.FLUORITE)
                        .define('S', Items.STICK)
                        .pattern("  D")
                        .pattern(" F ")
                        .pattern("S  ")
                        .group(FLUORITE_GROUP)
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .save(output);
                shaped(RecipeCategory.MISC, ModItems.KATANA)
                        .define('F', ModItems.FLUORITE)
                        .define('S', Items.STICK)
                        .pattern("  F")
                        .pattern(" F ")
                        .pattern("S  ")
                        .group(FLUORITE_GROUP)
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .save(output);
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return "TutorialMod Recipes";
    }
}
