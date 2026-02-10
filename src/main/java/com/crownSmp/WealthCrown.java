package com.crownSmp;

import net.minecraft.item.Item;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jspecify.annotations.Nullable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.util.math.Box;
import java.util.List;

public class WealthCrown extends Item {
    public WealthCrown(Settings settings) {
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
                        StatusEffects.HERO_OF_THE_VILLAGE, 0, 0, false, false, false));

                if (player.hurtTime > 0 && player.getRandom().nextFloat() < 1) { // 100% chance per hit
                    spawnGuard(player, world);
                }
            }
        }
    }

        private void spawnGuard(PlayerEntity player, ServerWorld world) {
            Box box = player.getBoundingBox().expand(20.0);

            // Find golems that are player-created
            List<IronGolemEntity> golems = world.getEntitiesByType(EntityType.IRON_GOLEM, box,
                    IronGolemEntity::isPlayerCreated);

            if (golems.size() < 3) {
                IronGolemEntity golem = EntityType.IRON_GOLEM.spawn(world, player.getBlockPos(), net.minecraft.entity.SpawnReason.MOB_SUMMONED);
                if (golem != null) {
                    golem.setPlayerCreated(true); // Prevents it from attacking the player
                    golem.setCustomName(Text.literal("Wealth Guard").formatted(Formatting.GREEN));
                    golem.setCustomNameVisible(true);

                    Scoreboard scoreboard = world.getScoreboard();
                    String teamName = "wealth_guards";
                    Team team = scoreboard.getTeam(teamName);

                    // Create the team if it doesn't exist
                    if (team == null) {
                        team = scoreboard.addTeam(teamName);
                        team.setColor(Formatting.GREEN);
                    }

                    // 2. ADD GOLEM TO TEAM
                    // Use the UUID string or name for the scoreboard
                    scoreboard.addScoreHolderToTeam(golem.getUuidAsString(), team);

                    golem.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 0, false, false, false));
                    golem.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 600, 0, false, false, false));

                    // Set the golem's target to the player's attacker if they exist
                    if (player.getAttacker() != null) {
                        golem.setTarget(player.getAttacker());
                    }
                }
            }
        }
    }