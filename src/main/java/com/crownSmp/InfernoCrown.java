package com.crownSmp;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.server.world.ServerWorld;
import org.jspecify.annotations.Nullable;

public class InfernoCrown extends Item {
    public InfernoCrown(Settings settings) {
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
                        StatusEffects.FIRE_RESISTANCE, 0, 0, false, false, false));

                // Smelting Items
                if (player.isSneaking() && world.getTime() % 50 == 0) {
                    ItemStack handStack = player.getMainHandStack();

                    if (!handStack.isEmpty()) {
                        ItemStack result = getSmeltedResult(world, handStack);

                        // If the result is different from the original, smelting happened
                        if (result != handStack) {
                            // Shrink original stack by 1
                            handStack.decrement(1);
                            // Give the player 1 of the cooked version
                            player.getInventory().offerOrDrop(result.copyWithCount(1));

                            // Visual/Sound feedback
                            world.playSound(null, player.getBlockPos(),
                                    net.minecraft.sound.SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE,
                                    net.minecraft.sound.SoundCategory.PLAYERS, 1.0f, 1.0f);
                        }
                    }
                }
            }
        }
    }
    private ItemStack getSmeltedResult(ServerWorld world, ItemStack stack) {
        // 1. Wrap the stack in the new 1.21 input type
        SingleStackRecipeInput input = new SingleStackRecipeInput(stack);

        // 2. Query the RecipeManager using the new input wrapper
        return world.getRecipeManager()
                .getFirstMatch(RecipeType.SMELTING, input, world)
                .map(recipeEntry -> {
                    // 1.21 uses RecipeEntry; we need to call .value() to get the actual recipe
                    ItemStack result = recipeEntry.value().craft(input, world.getRegistryManager());
                    // Optional: Ensure the result stack size matches the input if needed
                    return result;
                })
                .orElse(stack);
    }
}