package net.firemuffin303.muffinsmcapi.fabric;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.fabricmc.api.ModInitializer;
import net.firemuffin303.muffinsmcapi.api.BlockEntityTypeUtil;
import net.firemuffin303.muffinsmcapi.api.BoatRegistry;
import net.firemuffin303.muffinsmcapi.fabric.api.FabricOvenRegistry;
import net.firemuffin303.muffinsmcapi.impl.registration.OvenRegistration;
import net.firemuffin303.muffinsmcapi.impl.registration.RegistryHolder;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.minecraft.core.Registry;

public final class MuffinsmcapiFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        FabricOvenRegistry.init();

        OvenRegistration.setPlatformHandler(new OvenRegistration.RegistryPlatformHandler() {
            @Override
            public <T> void register(ResourceRegistry<T> registry) {
                Registry<T> registry1 = null;

                if(registry.getResource() == BoatRegistry.BOAT_VARIANT_MAP){
                    registry1 = (Registry<T>) FabricOvenRegistry.OVEN_BOAT_VARIANT_REGISTRY;
                }

                if(registry1 == null){
                    registry1 = registry.resolveRegistry();
                }


                Registry<T> finalRegistry = registry1;
                registry.getValues().forEach(tRegistryHolder -> {
                    Registry.register(finalRegistry,tRegistryHolder.getResourceLocation(),tRegistryHolder.resolve());
                });
            }
        });



        MuffinsMcAPI.init();
        BlockEntityTypeUtil.implementBlockEntityType();

    }

    private static  <T> void registerResources(ResourceRegistry<T> registry) {
        for(RegistryHolder<T> registryHolder : registry.getValues()){
            Registry.register(registry.resolveRegistry(),registryHolder.getResourceLocation(),registryHolder.resolve());
        }
    }


}
