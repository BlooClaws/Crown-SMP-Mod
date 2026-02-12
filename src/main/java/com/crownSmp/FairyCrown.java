package com.crownSmp;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import org.jspecify.annotations.Nullable;

public class FairyCrown extends Item {
    public FairyCrown(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        super.inventoryTick(stack, world, entity, slot);

        // Fairy crown effects
        if (!world.isClient() && entity instanceof PlayerEntity player) {
            ItemStack headStack = player.getEquippedStack(EquipmentSlot.HEAD);
            if (headStack == stack) {
                System.out.println();
            }
        }
    }
}
