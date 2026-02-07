package com.crownSmp;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModItems {

    public static final String MOD_ID = "crown_smp";

    public static final Item AGILITY_CROWN = new Item(new Item.Settings());

    public static void registerItems() {
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "agility_crown"), AGILITY_CROWN);
    }
}
