package com.crownSmp;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;


public class ModItems {

    public static final String MOD_ID = "crown_smp";

    public static final Item AGILITY_CROWN = new Item(new Item.Settings()
            .maxCount(1)
            .equippable(EquipmentSlot.HEAD)
            .component(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder()
                    .add(EntityAttributes.ARMOR,
                            new EntityAttributeModifier(Identifier.of(MOD_ID, "agility_crown_armor"),
                                    3.0,
                            EntityAttributeModifier.Operation.ADD_VALUE),
                            AttributeModifierSlot.HEAD)

                    .add(EntityAttributes.ATTACK_SPEED,
                            new EntityAttributeModifier(
                                    Identifier.of(MOD_ID, "agility_crown_attack_speed"),
                                    0.3,
                                    EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                            AttributeModifierSlot.HEAD)

                    .add(EntityAttributes.MOVEMENT_SPEED,
                            new EntityAttributeModifier(
                            Identifier.of(MOD_ID, "agility_crown_speed"),
                                    0.2,
                                    EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                            AttributeModifierSlot.HEAD)
                    .build()

    ));
    public static final Item STRENGTH_CROWN = new Item(new Item.Settings()
            .maxCount(1)
            .equippable(EquipmentSlot.HEAD)
    );
    public static final Item INFERNO_CROWN = new Item(new Item.Settings()
            .maxCount(1)
            .equippable(EquipmentSlot.HEAD)
    );
    public static final Item HEALTH_CROWN = new Item(new Item.Settings()
            .maxCount(1)
            .equippable(EquipmentSlot.HEAD)
    );
    public static final Item OCEAN_CROWN = new Item(new Item.Settings()
            .maxCount(1)
            .equippable(EquipmentSlot.HEAD)
    );
    public static final Item WEALTH_CROWN = new Item(new Item.Settings()
            .maxCount(1)
            .equippable(EquipmentSlot.HEAD)
    );

    public static void registerItems() {
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "agility_crown"), AGILITY_CROWN);
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "strength_crown"), STRENGTH_CROWN);
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "inferno_crown"), INFERNO_CROWN);
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "health_crown"), HEALTH_CROWN);
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "ocean_crown"), OCEAN_CROWN);
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "wealth_crown"), WEALTH_CROWN);
    }
}
