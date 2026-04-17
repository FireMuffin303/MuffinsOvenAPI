package net.firemuffin303.muffinsmcapi.fabric.api;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.firemuffin303.muffinsmcapi.api.BoatRegistry;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.minecraft.core.Registry;

public class FabricOvenRegistry {
    public static final Registry<OvenBoatVariant> OVEN_BOAT_VARIANT_REGISTRY = FabricRegistryBuilder.createSimple(BoatRegistry.OVEN_BOAT_VARIANT).buildAndRegister();

    public static void init(){}
}
