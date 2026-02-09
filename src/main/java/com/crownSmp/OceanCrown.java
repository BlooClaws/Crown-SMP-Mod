package com.crownSmp;

import net.minecraft.item.Item;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import org.jspecify.annotations.Nullable;

public class OceanCrown extends Item {
    public OceanCrown(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        super.inventoryTick(stack, world, entity, slot);

        // Fire Resistance
        if (!world.isClient() && entity instanceof PlayerEntity player) {
            ItemStack headStack = player.getEquippedStack(EquipmentSlot.HEAD);
            if (headStack == stack) {
                // Apply Fire Resistance for 10 seconds
                player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.DOLPHINS_GRACE, 0, 0, false, false, false));
                player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.WATER_BREATHING, 0, 0, false, false, false));
                player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.NIGHT_VISION, 0, 0, false, false, false));
            }
        }
    }
}