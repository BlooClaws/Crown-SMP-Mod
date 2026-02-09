package com.crownSmp;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CrownSMP implements ModInitializer {
	public static final String MOD_ID = "crown_smp";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModItems.registerItems();

		// FIRE ASPECT LOGIC: Triggered when ANY entity is hit
		AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			// We only care about what happens on the server
			if (!world.isClient()) {
				ItemStack headStack = player.getEquippedStack(EquipmentSlot.HEAD);

				// Check if the player is wearing your specific crown
				if (headStack.getItem() instanceof InfernoCrown) {
					// Seconds Enemy is on Fire
					entity.setOnFireFor(4.0f);
				}
			}
			return ActionResult.PASS;
		});

		net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.END_SERVER_TICK.register(server -> {
			for (ServerWorld world : server.getWorlds()) {
				for (Entity entity : world.iterateEntities()) {
					if (entity instanceof IronGolemEntity golem && golem.isPlayerCreated()) {
						// If the "timer" (Luck effect) is gone, discard the golem
						if (!golem.hasStatusEffect(StatusEffects.SPEED)) {
							// Visual effect before they vanish
							world.spawnParticles(net.minecraft.particle.ParticleTypes.POOF,
									golem.getX(), golem.getY() + 1, golem.getZ(), 10, 0.2, 0.2, 0.2, 0.1);
							golem.discard();


							LOGGER.info("Hello Fabric world!");
						}
					}
				}
			}
		});
	}
}