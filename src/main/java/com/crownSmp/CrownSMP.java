package com.crownSmp;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CrownSMP implements ModInitializer {
	public static final String MOD_ID = "crown_smp";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final ComponentType<Unit> SOUL_BOUND = Registry.register(
			Registries.DATA_COMPONENT_TYPE,
			Identifier.of(MOD_ID, "soul_bound"),
			ComponentType.<Unit>builder().codec(Unit.CODEC)
					.build());

	@Override
	public void onInitialize() {

		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModItems.registerItems();

		// Fire aspect no matter what is held
		AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			if (!world.isClient()) {
				ItemStack headStack = player.getEquippedStack(EquipmentSlot.HEAD);

				// Checks if the player is wearing the inferno crown
				if (headStack.getItem() instanceof InfernoCrown) {
					// How many seconds of fire is applied (4.0 is equal to fire aspect one)
					entity.setOnFireFor(4.0f);
				}
			}
			return ActionResult.PASS;
		});

		net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.END_SERVER_TICK.register(server -> {
			for (ServerWorld world : server.getWorlds()) {
				for (Entity entity : world.iterateEntities()) {
					if (entity instanceof IronGolemEntity golem && golem.isPlayerCreated()) {
						// Using the applied speed and glow effect as a built-in timer
						if (!golem.hasStatusEffect(StatusEffects.SPEED)) {
							// Visual effect before they vanish
							world.getScoreboard().clearTeam(golem.getUuidAsString());

							world.spawnParticles(ParticleTypes.HAPPY_VILLAGER,
									golem.getX(), golem.getY() + 1, golem.getZ(), 20, 0.5, 0.5, 0.5, 0.1);
							golem.discard();

							Team wealthTeam = world.getScoreboard().getTeam("wealth_guards");
							if (wealthTeam != null) world.getScoreboard().addScoreHolderToTeam(golem.getNameForScoreboard(), wealthTeam);

						}
					}
				}
			}
		});

		LOGGER.info("Hello Fabric world!");

	}
}