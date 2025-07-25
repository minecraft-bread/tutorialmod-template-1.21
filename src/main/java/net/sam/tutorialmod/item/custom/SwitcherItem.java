package net.sam.tutorialmod.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Map;

public class SwitcherItem extends Item {
    public static final Map<EntityType<?>, EntityType<?>> SWITCHER_MAP =
            Map.of(
                    EntityType.PIG, EntityType.BREEZE_WIND_CHARGE,
                    EntityType.CREEPER, EntityType.END_CRYSTAL,
                    EntityType.CHICKEN, EntityType.EGG,
                    EntityType.COW, EntityType.WITCH
            );

    public SwitcherItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        World world = user.getWorld();
        EntityType<?> originalType = entity.getType();

        if (!world.isClient && SWITCHER_MAP.containsKey(originalType)) {
            EntityType<?> newType = SWITCHER_MAP.get(originalType);

            // Remove old entity
            entity.discard();

            // Spawn new entity at same location
            Entity newEntity = newType.create(world);
            if (newEntity != null) {
                newEntity.refreshPositionAndAngles(entity.getX(), entity.getY(), entity.getZ(), entity.getYaw(), entity.getPitch());
                world.spawnEntity(newEntity);
            }

            // Play sound
            world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}