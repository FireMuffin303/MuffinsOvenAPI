package net.firemuffin303.muffinsmcapi;

import net.minecraft.resources.ResourceLocation;

public final class MuffinsMcAPI {
    public static final String MOD_ID = "muffinsmcapi";


    public static void init() {

    }

    public static ResourceLocation modid(String id){
        return new ResourceLocation(MOD_ID,id);
    }
}
