package com.crownSmp;

import net.minecraft.component.type.DeathProtectionComponent;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;

import java.util.Collections;
import java.util.List;


public class ModItems {
    public static final String MOD_ID = "crown_smp";

    // 1. Declare the items here (but don't initialize them yet)
    public static Item AGILITY_CROWN;
    public static Item STRENGTH_CROWN;
    public static Item INFERNO_CROWN;
    public static Item HEALTH_CROWN;
    public static Item OCEAN_CROWN;
    public static Item WEALTH_CROWN;

    // 2. A helper method to register and initialize at the same time
    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), item);
    }

    public static void registerItems() {

        AGILITY_CROWN = register("agility_crown", new Item(new Item.Settings()
                .maxCount(1)
                .equippable(EquipmentSlot.HEAD)
                .component(DataComponentTypes.LORE, new LoreComponent(List.of(
                        Text.translatable("item.crown_smp.agility_crown").formatted(Formatting.BLUE, Formatting.ITALIC))))
                .component(DataComponentTypes.DEATH_PROTECTION, new DeathProtectionComponent(Collections.emptyList()))
                .component(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder()
                        .add(EntityAttributes.ARMOR, new EntityAttributeModifier(Identifier.of(MOD_ID, "agility_crown_armor"), 3.0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.ATTACK_SPEED, new EntityAttributeModifier(Identifier.of(MOD_ID, "agility_crown_attack_speed"), 0.3, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_ID, "agility_crown_speed"), 0.2, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.MAX_HEALTH, new EntityAttributeModifier(Identifier.of(MOD_ID, "agility_crown_max_health"), 4.0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE), AttributeModifierSlot.HEAD)
                        .build())));

        STRENGTH_CROWN = register("strength_crown", new Item(new Item.Settings()
                .maxCount(1)
                .equippable(EquipmentSlot.HEAD)
                .component(DataComponentTypes.LORE, new LoreComponent(List.of(
                        Text.translatable("item.crown_smp.strength_crown").formatted(Formatting.RED, Formatting.ITALIC))))
                .component(DataComponentTypes.DEATH_PROTECTION, new DeathProtectionComponent(Collections.emptyList()))
                .component(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder()
                        .add(EntityAttributes.ARMOR, new EntityAttributeModifier(Identifier.of(MOD_ID, "strength_crown_armor"), 3.0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.ATTACK_DAMAGE, new EntityAttributeModifier(Identifier.of(MOD_ID, "strength_crown_attack_damage"), 4.5, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.MAX_HEALTH, new EntityAttributeModifier(Identifier.of(MOD_ID, "strength_crown_max_health"), -2.0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE), AttributeModifierSlot.HEAD)
                        .build())));

        INFERNO_CROWN = register("inferno_crown", new InfernoCrownItem(new Item.Settings()
                .maxCount(1)
                .equippable(EquipmentSlot.HEAD)
                .component(DataComponentTypes.LORE, new LoreComponent(List.of(
                        Text.translatable("item.crown_smp.inferno_crown").formatted(Formatting.GOLD, Formatting.ITALIC))))
                .component(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder()
                        .add(EntityAttributes.ARMOR, new EntityAttributeModifier(Identifier.of(MOD_ID, "inferno_crown_armor"), 3.0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .build())));

        HEALTH_CROWN = register("health_crown", new Item(new Item.Settings().maxCount(1).equippable(EquipmentSlot.HEAD)));

        OCEAN_CROWN = register("ocean_crown", new Item(new Item.Settings().maxCount(1).equippable(EquipmentSlot.HEAD)));

        WEALTH_CROWN = register("wealth_crown", new Item(new Item.Settings().maxCount(1).equippable(EquipmentSlot.HEAD)));
    }
}