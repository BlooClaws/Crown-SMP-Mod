package com.crownSmp;

import net.minecraft.component.type.DeathProtectionComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;

import java.util.Collections;


public class ModItems {
    public static final String MOD_ID = "crown_smp";

    // 1. Declare the items here (but don't initialize them yet)
    public static Item AGILITY_CROWN;
    public static Item STRENGTH_CROWN;
    public static Item INFERNO_CROWN;
    public static Item HEALTH_CROWN;
    public static Item OCEAN_CROWN;
    public static Item WEALTH_CROWN;


    private static Item register(String name, java.util.function.Function<Item.Settings, Item> factory, Item.Settings settings) {
        // Create the RegistryKey for the item
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, name));

        // Pass the key into settings and then create the item
        Item item = factory.apply(settings.registryKey(key));

        // Register the item using that same key
        return Registry.register(Registries.ITEM, key, item);
    }
    public static void registerItems() {

        AGILITY_CROWN = register("agility_crown", Item:: new, new Item.Settings()
                .maxCount(1)
                .equippable(EquipmentSlot.HEAD)
                .component(DataComponentTypes.CUSTOM_NAME, Text.literal("Agility Crown")
                        .formatted(Formatting.BLUE, Formatting.BOLD))
                .component(DataComponentTypes.DEATH_PROTECTION, new DeathProtectionComponent(Collections.emptyList()))
                .component(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder()
                        .add(EntityAttributes.ARMOR, new EntityAttributeModifier(Identifier.of(MOD_ID, "agility_crown_armor"),
                                3.0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.ATTACK_SPEED, new EntityAttributeModifier(Identifier.of(MOD_ID, "agility_crown_attack_speed"),
                                0.3, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_ID, "agility_crown_speed"),
                                0.3, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.MAX_HEALTH, new EntityAttributeModifier(Identifier.of(MOD_ID, "agility_crown_max_health"),
                                4, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .build()));

        STRENGTH_CROWN = register("strength_crown", Item:: new, new Item.Settings()
                .maxCount(1)
                .equippable(EquipmentSlot.HEAD)
                .component(DataComponentTypes.CUSTOM_NAME, Text.literal("Strength Crown")
                        .formatted(Formatting.RED, Formatting.BOLD))
                .component(DataComponentTypes.DEATH_PROTECTION, new DeathProtectionComponent(Collections.emptyList()))
                .component(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder()
                        .add(EntityAttributes.ARMOR, new EntityAttributeModifier(Identifier.of(MOD_ID, "strength_crown_armor"),
                                3.0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.ATTACK_DAMAGE, new EntityAttributeModifier(Identifier.of(MOD_ID, "strength_crown_attack_damage"),
                                4.5, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.MAX_HEALTH, new EntityAttributeModifier(Identifier.of(MOD_ID, "strength_crown_max_health"),
                                -2.0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .build()));

        INFERNO_CROWN = register("inferno_crown", InfernoCrown:: new, new Item.Settings()
                .maxCount(1)
                .equippable(EquipmentSlot.HEAD)
                .component(DataComponentTypes.CUSTOM_NAME, Text.literal("Inferno Crown")
                        .formatted(Formatting.GOLD, Formatting.BOLD))
                .component(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder()
                        .add(EntityAttributes.ARMOR, new EntityAttributeModifier(Identifier.of(MOD_ID, "inferno_crown_armor"),
                                3.0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.BURNING_TIME, new EntityAttributeModifier(Identifier.of(MOD_ID, "inferno_crown_burning_time"),
                                -0.97, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE), AttributeModifierSlot.HEAD)
                        .build()));

        HEALTH_CROWN = register("health_crown", Item:: new, new Item.Settings()
                .maxCount(1)
                .equippable(EquipmentSlot.HEAD)
                .component(DataComponentTypes.CUSTOM_NAME, Text.literal("Health Crown")
                        .formatted(Formatting.LIGHT_PURPLE, Formatting.BOLD))
                .component(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder()
                        .add(EntityAttributes.ARMOR, new EntityAttributeModifier(Identifier.of(MOD_ID, "health_crown_armor"),
                                3.0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.MAX_HEALTH, new EntityAttributeModifier(Identifier.of(MOD_ID, "health_crown_max_health"),
                                20, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .build()));
        OCEAN_CROWN = register("ocean_crown", Item:: new, new Item.Settings()
                .maxCount(1)
                .equippable(EquipmentSlot.HEAD)
                .component(DataComponentTypes.CUSTOM_NAME, Text.literal("Oceanic Crown")
                        .formatted(Formatting.DARK_BLUE, Formatting.BOLD)));

        WEALTH_CROWN = register("wealth_crown", Item:: new, new Item.Settings()
                .maxCount(1)
                .equippable(EquipmentSlot.HEAD)
                .component(DataComponentTypes.CUSTOM_NAME, Text.literal("Wealth Crown")
                        .formatted(Formatting.GREEN)));
    }
}