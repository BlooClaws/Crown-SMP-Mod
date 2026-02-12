package com.crownSmp;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import static com.crownSmp.CrownSMP.MOD_ID;

public class ModEntities {

    public static final EntityType<FairyCrownPet> FAIRY_CROWN_PET = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(MOD_ID, "fairy_crown_pet"),
            EntityType.Builder.create(FairyCrownPet::new, SpawnGroup.CREATURE)
                    .dimensions(0.6f, 1.2f) // width, height
                    .build(RegistryKey.of(
                            Registries.ENTITY_TYPE.getKey(),
                            Identifier.of(MOD_ID, "fairy_crown_pet")
                    ))
    );

    public static void init() {
    }
}
