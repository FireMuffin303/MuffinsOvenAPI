package net.firemuffin303.muffinsmcapi.fabric;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.fabricmc.api.ModInitializer;
import net.firemuffin303.muffinsmcapi.api.BlockEntityTypeUtil;
import net.firemuffin303.muffinsmcapi.api.BoatRegistry;
import net.firemuffin303.muffinsmcapi.fabric.api.CustomRegistryHelper;
import net.firemuffin303.muffinsmcapi.fabric.api.FabricOvenRegistry;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.minecraft.core.Registry;

public final class MuffinsmcapiFabric implements ModInitializer {

    private static final Registry<OvenBoatVariant> registryOvenBoat = FabricOvenRegistry.OVEN_BOAT_VARIANT_REGISTRY;
    public static final CustomRegistryHelper INSTANCE = new CustomRegistryHelper();
    private static final Registry<OvenBoatVariant> OVEN_BOAT_VARIANT_REGISTRY = INSTANCE.register(BoatRegistry.OVEN_BOAT_VARIANT,registryOvenBoat);


    @Override
    public void onInitialize() {
        MuffinsMcAPI.init();
    }
}
