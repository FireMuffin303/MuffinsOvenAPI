package net.firemuffin303.muffinsmcapi.api;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class BoatRegistry {
    /*
    Boat Registration is modified from Terraform Wood API.
    */

    public static final ResourceKey<Registry<OvenBoatVariant>> OVEN_BOAT_VARIANT = ResourceKey.createRegistryKey(MuffinsMcAPI.modid("oven_boat_type"));


    public static void init(){}



}
