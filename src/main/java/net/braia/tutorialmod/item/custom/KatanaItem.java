package net.braia.tutorialmod.item.custom;

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

        target.level().addFreshEntity(newChicken);

        return InteractionResult.SUCCESS;
    }
}
