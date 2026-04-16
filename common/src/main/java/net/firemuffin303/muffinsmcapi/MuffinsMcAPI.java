package net.firemuffin303.muffinsmcapi;

import net.firemuffin303.muffinsmcapi.impl.entity.boat.BoatRegistry;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatUtil;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.firemuffin303.muffinsmcapi.util.PlatformUtil;
import net.minecraft.core.Registry;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public final class MuffinsMcAPI {
    public static final String MOD_ID = "muffins_ovenapi";

    public static void init() {
        BoatRegistry.init();
        EntityDataSerializers.registerSerializer(OvenBoatUtil.SERIALIZER);
    }

    public static ResourceLocation modid(String id){
        return new ResourceLocation(MOD_ID,id);
    }
}
