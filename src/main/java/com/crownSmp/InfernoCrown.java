package com.crownSmp;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
/* import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.recipe.input.SingleStackRecipeInput; */
import net.minecraft.server.world.ServerWorld;
// import net.minecraft.util.Hand;
// import net.minecraft.world.World;
import org.jspecify.annotations.Nullable;
// import java.util.Optional;

public class InfernoCrown extends Item {
    public InfernoCrown(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        super.inventoryTick(stack, world, entity, slot);

        if (!world.isClient() && entity instanceof PlayerEntity player) {
            ItemStack headStack = player.getEquippedStack(EquipmentSlot.HEAD);
            if (headStack == stack) {
                // Apply Fire Resistance for 200 ticks (10 seconds)
                // Duration is kept short so it disappears quickly when the crown is removed
                player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.FIRE_RESISTANCE, 200, 0, false, false, false));

                // if (player.isSneaking()) {
                // cookItemInHand(player, world);

            }
        }
    }
}
    /*private void cookItemInHand(PlayerEntity player, World world) {
        // Check every 40 ticks (2 seconds) to avoid instant smelting
        if (world.getTime() % 40 != 0) return;

        ItemStack mainHand = player.getMainHandStack();
        if (mainHand.isEmpty()) return;

        // 1.21 uses SingleStackRecipeInput for cooking recipes
        SingleStackRecipeInput input = new SingleStackRecipeInput(mainHand);

        // Look for a valid smelting recipe
        Optional<RecipeEntry<SmeltingRecipe>> match = world.getRecipeManager()
                .getSynchronizedRecipes().getFirstMatch(RecipeType.SMELTING, input, world);

        if (match.isPresent()) {
            // Get the output stack from the recipe
            ItemStack recipeResult = match.get().value().getOutput(world.getRegistryManager());

            if (!recipeResult.isEmpty()) {
                // Create a copy of the result to avoid modifying the recipe's base stack
                ItemStack resultStack = recipeResult.copy();

                // Handle the item swap
                mainHand.decrement(1); // Remove 1 from the input stack

                if (mainHand.isEmpty()) {
                    // If input is gone, put result directly in hand
                    player.setStackInHand(Hand.MAIN_HAND, resultStack);
                } else {
                    // If input remains, give result to player (drops on floor if inventory full)
                    player.getInventory().offerOrDrop(resultStack);
                }

                // Optional: Play a sound or add particles for feedback
                // world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 0.5f, 1.0f);
            }
        }
    } */