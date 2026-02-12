package com.crownSmp;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
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

    public static final RegistryKey<EntityType<?>> FAIRY_PET_KEY = RegistryKey.of(
            RegistryKeys.ENTITY_TYPE,
            Identifier.of("crown_smp", "fairy_pet")
    );

    // Register the Entity using that Key
    public static final EntityType<FairyCrownPet> FAIRY_PET_ENTITY_TYPE = Registry.register(
            Registries.ENTITY_TYPE,
            FAIRY_PET_KEY, // Use the key here
            EntityType.Builder.create(FairyCrownPet::new, SpawnGroup.CREATURE)
                    .dimensions(0.6f, 0.6f)
                    .build(FAIRY_PET_KEY) // Pass the key here as the argument
    );

    @Override
    public void onInitialize() {

        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        ModItems.registerItems();

        ModEntities.init();

        FabricDefaultAttributeRegistry.register(
                ModEntities.FAIRY_CROWN_PET,
                FairyCrownPet.createAttributes()
        );

        UseItemCallback.EVENT.register((player, world, hand) ->

        {
            if (!world.isClient()) {
                ItemStack head = player.getEquippedStack(EquipmentSlot.HEAD);

                if (head.getItem() instanceof FairyCrown) {
                    if (player.isSneaking()) {
                        System.out.println("Crown ability activated!");
                    }
                }
            }
            return ActionResult.PASS;
        });

        // Fire aspect no matter what is held
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) ->

        {
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


        net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.END_SERVER_TICK.register(server ->

        {
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
                            if (wealthTeam != null)
                                world.getScoreboard().addScoreHolderToTeam(golem.getNameForScoreboard(), wealthTeam);

                        }
                    }
                }
            }
        });

        LOGGER.info("Hello Fabric world!");

    }
}