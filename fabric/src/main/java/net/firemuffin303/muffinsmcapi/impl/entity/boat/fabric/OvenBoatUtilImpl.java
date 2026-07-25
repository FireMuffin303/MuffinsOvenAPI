package net.firemuffin303.muffinsmcapi.impl.entity.boat.fabric;

import net.firemuffin303.muffinsmcapi.fabric.MuffinsmcapiFabric;
import net.firemuffin303.muffinsmcapi.fabric.api.FabricOvenRegistry;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.Set;

public class OvenBoatUtilImpl {

    public static ResourceLocation getBoatKey(OvenBoatVariant ovenBoatVariant) {
        return FabricOvenRegistry.OVEN_BOAT_VARIANT_REGISTRY.getKey(ovenBoatVariant);
    }

    public static OvenBoatVariant getBoat(ResourceLocation resourceLocation) {
        return FabricOvenRegistry.OVEN_BOAT_VARIANT_REGISTRY.get(resourceLocation);
    }

    public static Set<Map.Entry<ResourceKey<OvenBoatVariant>, OvenBoatVariant>> entrySet() {
        return FabricOvenRegistry.OVEN_BOAT_VARIANT_REGISTRY.entrySet();
    }

    public static boolean hasBoat(ResourceLocation resourceLocation) {
        return FabricOvenRegistry.OVEN_BOAT_VARIANT_REGISTRY.containsKey(resourceLocation);
    }
}
