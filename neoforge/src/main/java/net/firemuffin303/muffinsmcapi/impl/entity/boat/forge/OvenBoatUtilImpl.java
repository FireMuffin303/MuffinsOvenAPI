package net.firemuffin303.muffinsmcapi.impl.entity.boat.forge;

import net.firemuffin303.muffinsmcapi.forge.MuffinsOvenAPINeoForge;
import net.firemuffin303.muffinsmcapi.api.BoatRegistry;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class OvenBoatUtilImpl {
    public static ResourceLocation getBoatKey(OvenBoatVariant ovenBoatVariant) {
        return BoatRegistry.OVEN_BOAT_REGISTRY.getKey(ovenBoatVariant);
    }

    public static OvenBoatVariant getBoat(ResourceLocation resourceLocation) {
        return BoatRegistry.OVEN_BOAT_REGISTRY.get(resourceLocation);
    }

    public static Set<Map.Entry<ResourceKey<OvenBoatVariant>, OvenBoatVariant>> entrySet() {
        return BoatRegistry.OVEN_BOAT_REGISTRY.entrySet();
    }

    public static boolean hasBoat(ResourceLocation resourceLocation) {
        return BoatRegistry.OVEN_BOAT_REGISTRY.containsKey(resourceLocation);
    }
}
