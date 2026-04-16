package net.firemuffin303.muffinsmcapi.impl.entity.boat.fabric;

import net.firemuffin303.muffinsmcapi.fabric.MuffinsmcapiFabric;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.Set;

public class BoatRegistryImpl {

    public static ResourceLocation getBoatKey(OvenBoatVariant ovenBoatVariant) {
        return MuffinsmcapiFabric.OVEN_BOAT_VARIANT_REGISTRY.getKey(ovenBoatVariant);
    }

    public static OvenBoatVariant getBoat(ResourceLocation resourceLocation) {
        return MuffinsmcapiFabric.OVEN_BOAT_VARIANT_REGISTRY.get(resourceLocation);
    }

    public static Set<Map.Entry<ResourceKey<OvenBoatVariant>, OvenBoatVariant>> entrySet() {
        return MuffinsmcapiFabric.OVEN_BOAT_VARIANT_REGISTRY.entrySet();
    }
}
