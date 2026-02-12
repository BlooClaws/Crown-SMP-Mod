package com.crownSmp;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
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
import net.minecraft.util.Unit;


public class ModItems {
    public static final String MOD_ID = "crown_smp";

    // Declaring the items before initializing them
    public static Item AGILITY_CROWN;
    public static Item STRENGTH_CROWN;
    public static Item INFERNO_CROWN;
    public static Item HEALTH_CROWN;
    public static Item OCEAN_CROWN;
    public static Item WEALTH_CROWN;
    public static Item ICE_CROWN;
    public static Item FAIRY_CROWN;
    public static Item GOBLIN_CROWN;
    public static Item FAIRY_CROWN_PET_SPAWN_EGG;


    private static Item register(String name, java.util.function.Function<Item.Settings, Item> factory, Item.Settings settings) {

        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, name));

        Item item = factory.apply(settings.registryKey(key));

        return Registry.register(Registries.ITEM, key, item);
    }

    public static void registerItems() {
// Crown #1
        AGILITY_CROWN = register("agility_crown", Item::new, new Item.Settings()
                .maxCount(1) // Sets stack count to 1
                .equippable(EquipmentSlot.HEAD) // Makes equippable to helmet slot
                .component(DataComponentTypes.CUSTOM_NAME, Text.literal("Agility Crown") // Turns the text blue in color and bold
                        .formatted(Formatting.BLUE, Formatting.BOLD))
                .component(CrownSMP.SOUL_BOUND, Unit.INSTANCE) // Keeps the item on death
                .component(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder()
                        .add(EntityAttributes.ARMOR, new EntityAttributeModifier(Identifier.of(MOD_ID, "agility_crown_armor"), // Sets crown to diamond helmet level
                                3.0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.ATTACK_SPEED, new EntityAttributeModifier(Identifier.of(MOD_ID, "agility_crown_attack_speed"), // Increases attack speed by 30%
                                0.5, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_ID, "agility_crown_speed"), // Increases movement speed by 50%
                                0.3, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.MAX_HEALTH, new EntityAttributeModifier(Identifier.of(MOD_ID, "agility_crown_max_health"), // Increases health by 2 hearts
                                4, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD) // AttributeModifierSlot.HEAD makes it so you only get the effects when wearing it
                        .build()));

        STRENGTH_CROWN = register("strength_crown", Item::new, new Item.Settings()
                .maxCount(1)
                .equippable(EquipmentSlot.HEAD)
                .component(DataComponentTypes.CUSTOM_NAME, Text.literal("Strength Crown")
                        .formatted(Formatting.RED, Formatting.BOLD))
                .component(CrownSMP.SOUL_BOUND, Unit.INSTANCE)
                .component(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder()
                        .add(EntityAttributes.ARMOR, new EntityAttributeModifier(Identifier.of(MOD_ID, "strength_crown_armor"),
                                3.0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.ATTACK_DAMAGE, new EntityAttributeModifier(Identifier.of(MOD_ID, "strength_crown_attack_damage"), // Increases attack damage by 450% (between strength 1 and 2)
                                4.5, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.MAX_HEALTH, new EntityAttributeModifier(Identifier.of(MOD_ID, "strength_crown_max_health"), // Decreases health by 1 heart
                                -2.0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .build()));

        INFERNO_CROWN = register("inferno_crown", InfernoCrown::new, new Item.Settings() // Instead of Item:: new it is InfernoCrown:: new so we can call InfernoCrown.java for fire res and smelting
                .maxCount(1)
                .equippable(EquipmentSlot.HEAD)
                .component(DataComponentTypes.CUSTOM_NAME, Text.literal("Inferno Crown")
                        .formatted(Formatting.GOLD, Formatting.BOLD))
                .component(CrownSMP.SOUL_BOUND, Unit.INSTANCE)
                .component(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder()
                        .add(EntityAttributes.ARMOR, new EntityAttributeModifier(Identifier.of(MOD_ID, "inferno_crown_armor"),
                                3.0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.BURNING_TIME, new EntityAttributeModifier(Identifier.of(MOD_ID, "inferno_crown_burning_time"), // Decreases burning time by 97% (only for visual since fire res is applied)
                                -0.97, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE), AttributeModifierSlot.HEAD)
                        .build()));

        HEALTH_CROWN = register("health_crown", Item::new, new Item.Settings()
                .maxCount(1)
                .equippable(EquipmentSlot.HEAD)
                .component(DataComponentTypes.CUSTOM_NAME, Text.literal("Health Crown")
                        .formatted(Formatting.LIGHT_PURPLE, Formatting.BOLD))
                .component(CrownSMP.SOUL_BOUND, Unit.INSTANCE)
                .component(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder()
                        .add(EntityAttributes.ARMOR, new EntityAttributeModifier(Identifier.of(MOD_ID, "health_crown_armor"),
                                3.0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.MAX_HEALTH, new EntityAttributeModifier(Identifier.of(MOD_ID, "health_crown_max_health"), // Increases health by 10 hearts
                                20, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .build()));

        OCEAN_CROWN = register("ocean_crown", OceanCrown::new, new Item.Settings()
                .maxCount(1)
                .equippable(EquipmentSlot.HEAD)
                .component(DataComponentTypes.CUSTOM_NAME, Text.literal("Oceanic Crown")
                        .formatted(Formatting.DARK_BLUE, Formatting.BOLD))
                .component(CrownSMP.SOUL_BOUND, Unit.INSTANCE)
                .component(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder()
                        .add(EntityAttributes.ARMOR, new EntityAttributeModifier(Identifier.of(MOD_ID, "ocean_crown_armor"),
                                3.0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.WATER_MOVEMENT_EFFICIENCY, new EntityAttributeModifier(Identifier.of(MOD_ID, "ocean_crown_water_movement_efficiency"), // Only effects walking speed for some reason
                                1.0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.SUBMERGED_MINING_SPEED, new EntityAttributeModifier(Identifier.of(MOD_ID, "ocean_crown_submerged_mining_speed"), // Increased mining efficiency underwater
                                4.0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .build()));

        WEALTH_CROWN = register("wealth_crown", WealthCrown::new, new Item.Settings()
                .maxCount(1)
                .equippable(EquipmentSlot.HEAD)
                .component(DataComponentTypes.CUSTOM_NAME, Text.literal("Wealth Crown")
                        .formatted(Formatting.DARK_GREEN, Formatting.BOLD))
                .component(CrownSMP.SOUL_BOUND, Unit.INSTANCE)
                .component(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder()
                        .add(EntityAttributes.ARMOR, new EntityAttributeModifier(Identifier.of(MOD_ID, "wealth_crown_armor"),
                                3.0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.BLOCK_INTERACTION_RANGE, new EntityAttributeModifier(Identifier.of(MOD_ID, "wealth_crown_block_interaction_range"), // Increases reach by 3 but not hit range
                                3.0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.LUCK, new EntityAttributeModifier(Identifier.of(MOD_ID, "wealth_crown_luck"), // Increased luck by 2
                                2.0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .build()));

        ICE_CROWN = register("ice_crown", Item::new, new Item.Settings()
                .maxCount(1)
                .equippable(EquipmentSlot.HEAD)
                .component(DataComponentTypes.CUSTOM_NAME, Text.literal("Ice Crown")
                        .formatted(Formatting.AQUA, Formatting.BOLD))
                .component(CrownSMP.SOUL_BOUND, Unit.INSTANCE)
                .component(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder()
                        .add(EntityAttributes.ARMOR, new EntityAttributeModifier(Identifier.of(MOD_ID, "ice_crown_armor"),
                                3.0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.BURNING_TIME, new EntityAttributeModifier(Identifier.of(MOD_ID, "ice_crown_burning_time"),
                                -0.5, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.FALL_DAMAGE_MULTIPLIER, new EntityAttributeModifier(Identifier.of(MOD_ID, "ice_crown_fall_damage_multiplier"),
                                0.25, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE), AttributeModifierSlot.HEAD)
                        .build()));

        FAIRY_CROWN = register("fairy_crown", FairyCrown::new, new Item.Settings()
                .maxCount(1)
                .equippable(EquipmentSlot.HEAD)
                .component(DataComponentTypes.CUSTOM_NAME, Text.literal("Fairy Crown")
                        .formatted(Formatting.DARK_PURPLE, Formatting.BOLD))
                .component(CrownSMP.SOUL_BOUND, Unit.INSTANCE)
                .component(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder()
                        .add(EntityAttributes.ARMOR, new EntityAttributeModifier(Identifier.of(MOD_ID, "fairy_crown_armor"),
                                3.0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.FALL_DAMAGE_MULTIPLIER, new EntityAttributeModifier(Identifier.of(MOD_ID, "fairy_crown_fall_damage_multiplier"),
                                -1.0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.FLYING_SPEED, new EntityAttributeModifier(Identifier.of(MOD_ID, "fairy_crown_flying_speed"),
                                1.6, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .build()));
        GOBLIN_CROWN = register("goblin_crown", Item::new, new Item.Settings()
                .maxCount(1)
                .equippable(EquipmentSlot.HEAD)
                .component(DataComponentTypes.CUSTOM_NAME, Text.literal("Goblin Crown")
                        .formatted(Formatting.GREEN, Formatting.BOLD))
                .component(CrownSMP.SOUL_BOUND, Unit.INSTANCE)
                .component(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder()
                        .add(EntityAttributes.ARMOR, new EntityAttributeModifier(Identifier.of(MOD_ID, "goblin_crown_armor"),
                                3.0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.JUMP_STRENGTH, new EntityAttributeModifier(Identifier.of(MOD_ID, "goblin_crown_jump_strength"),
                                2.0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE), AttributeModifierSlot.HEAD)
                        .build()));

        FAIRY_CROWN_PET_SPAWN_EGG = Registry.register(
                Registries.ITEM,
                Identifier.of(MOD_ID, "fairy_pet_spawn_egg"),
                new SpawnEggItem(new Item.Settings())
        );
    }
}