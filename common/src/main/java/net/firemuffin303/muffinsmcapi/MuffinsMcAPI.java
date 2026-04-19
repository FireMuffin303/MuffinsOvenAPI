package net.firemuffin303.muffinsmcapi;

import net.firemuffin303.muffinsmcapi.api.BoatRegistry;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatUtil;
import net.minecraft.network.syncher.EntityDataSerializers;
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
