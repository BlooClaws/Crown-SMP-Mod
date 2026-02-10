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

        // Fire resistance
        if (!world.isClient() && entity instanceof PlayerEntity player) {
            ItemStack headStack = player.getEquippedStack(EquipmentSlot.HEAD);
            if (headStack == stack) {
                player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.FIRE_RESISTANCE, 0, 0, false, false, false));

                // Smelting Items
                if (player.isSneaking() && world.getTime() % 50 == 0) {
                    ItemStack handStack = player.getMainHandStack();

                    if (!handStack.isEmpty()) {
                        ItemStack result = getSmeltedResult(world, handStack);

                        if (result != handStack) {
                            // Takes 1 of the original stack
                            handStack.decrement(1);
                            // Gives the player 1 of the cooked version
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

        SingleStackRecipeInput input = new SingleStackRecipeInput(stack);

        return world.getRecipeManager()
                .getFirstMatch(RecipeType.SMELTING, input, world)
                .map(recipeEntry -> {
                    return recipeEntry.value().craft(input, world.getRegistryManager());
                })
                .orElse(stack);
    }
}