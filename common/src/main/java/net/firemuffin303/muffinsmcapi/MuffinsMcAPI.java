package net.firemuffin303.muffinsmcapi;

import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.firemuffin303.muffinsmcapi.util.PlatformUtil;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public final class MuffinsMcAPI {
    public static final String MOD_ID = "muffins_ovenapi";
    public static final ResourceKey<Registry<OvenBoatVariant>> OVEN_BOAT_VARIANT = ResourceKey.createRegistryKey(MuffinsMcAPI.modid("oven_boat_type"));
    public static final Registry<OvenBoatVariant> OVEN_BOAT_VARIANT_REGISTRY = PlatformUtil.initBoatVariantRegistry();

    public static void init() {

    }

    public static ResourceLocation modid(String id){
        return new ResourceLocation(MOD_ID,id);
    }
}
