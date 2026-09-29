package net.braia.tutorialmod.item;

import net.braia.tutorialmod.TutorialMod;
import net.braia.tutorialmod.food.ModFoods;
import net.braia.tutorialmod.item.custom.ChiselItem;
import net.braia.tutorialmod.item.custom.CoroteItem;
import net.braia.tutorialmod.item.custom.KatanaItem;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

import java.util.function.Function;

public class ModItems {
    private ModItems() {
    }
    // public static final Item FLUORITE = registerItem("fluorite", properties -> new Item(properties.axe));
    public static final Item FLUORITE = registerItem("fluorite", Item::new);
    public static final Item RAW_FLUORITE = registerItem("raw_fluorite", Item::new);

    public static final Item CHISEL = registerItem("chisel", properties -> new ChiselItem(properties.durability(32)));
    public static final Item KATANA = registerItem("katana", properties ->
            new KatanaItem(properties.durability(500).sword(ToolMaterial.DIAMOND, 6f, 1f)));

    public static final Item STRAWBERRY = registerItem("strawberry", properties ->
            new Item(properties.food(ModFoods.STRAWBERRY, ModFoods.STRAWBERRY_CONSUMABLE)));
    public static final Item SANDWICH = registerItem("sandwich", properties ->
            new Item(properties.food(ModFoods.SANDWICH, ModFoods.SANDWICH_CONSUMABLE)));
    public static final Item COROTE = registerItem("corote", properties ->
            new CoroteItem(properties.food(ModFoods.COROTE, ModFoods.COROTE_CONSUMABLE)));

    /* =============================================================================================================================================== */
    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name)))));
    }

    public static void registerModItem() {
        TutorialMod.LOGGER.info("Registering Mod Items for " + TutorialMod.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.accept(FLUORITE);
            output.accept(RAW_FLUORITE);
        });
    }
}
