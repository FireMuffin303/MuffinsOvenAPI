package net.firemuffin303.muffinsmcapi.impl.entity.boat.neoforge;

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
    public static final DeferredRegister<OvenBoatVariant> OVEN_BOAT_VARIANT = DeferredRegister.create(BoatRegistry.OVEN_BOAT_VARIANT,"");


    public static Supplier<OvenBoatVariant> registerBoatVariant(ResourceLocation id, Supplier<OvenBoatVariant> variantSupplier) {
        return OVEN_BOAT_VARIANT.register(id.getPath(),variantSupplier);
    }

    public static ResourceLocation getBoatKey(OvenBoatVariant ovenBoatVariant) {
        return MuffinsOvenAPINeoForge.OVEN_BOAT_VARIANTS_REGISTRY.getKey(ovenBoatVariant);
    }

    public static OvenBoatVariant getBoat(ResourceLocation resourceLocation) {
        return MuffinsOvenAPINeoForge.OVEN_BOAT_VARIANTS_REGISTRY.get(resourceLocation);
    }

    public static Set<Map.Entry<ResourceKey<OvenBoatVariant>, OvenBoatVariant>> entrySet() {
        return MuffinsOvenAPINeoForge.OVEN_BOAT_VARIANTS_REGISTRY.entrySet();
    }

    public static boolean hasBoat(ResourceLocation resourceLocation) {
        return MuffinsOvenAPINeoForge.OVEN_BOAT_VARIANTS_REGISTRY.containsKey(resourceLocation);
    }
}
