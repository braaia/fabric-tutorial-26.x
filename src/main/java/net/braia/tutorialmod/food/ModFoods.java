package net.braia.tutorialmod.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;

public class ModFoods {
    private ModFoods() {
    }

    public static final FoodProperties STRAWBERRY = new FoodProperties.Builder().nutrition(3).saturationModifier(0.3f).build();
    public static final FoodProperties SANDWICH = new FoodProperties.Builder().nutrition(8).saturationModifier(1).build();
    public static final FoodProperties COROTE = new FoodProperties.Builder().nutrition(1).saturationModifier(0.25f).build();

    public static final Consumable STRAWBERRY_CONSUMABLE = Consumables.defaultFood()
            .consumeSeconds(1f).onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 200), 0.15f)).build();
    public static final Consumable SANDWICH_CONSUMABLE = Consumables.defaultFood()
            .consumeSeconds(2.2f)
            .onConsume(new ApplyStatusEffectsConsumeEffect(
                    List.of(
                            new MobEffectInstance(MobEffects.HASTE, 300, 1),
                            new MobEffectInstance(MobEffects.SPEED, 300, 0)
                    )
            ))
            .build();
    public static final Consumable COROTE_CONSUMABLE = Consumables.defaultDrink().build();
}
