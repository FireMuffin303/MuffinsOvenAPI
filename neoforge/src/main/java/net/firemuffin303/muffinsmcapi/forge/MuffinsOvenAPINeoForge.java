package net.firemuffin303.muffinsmcapi.forge;

import com.mojang.logging.LogUtils;
import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.api.BoatRegistry;
import net.firemuffin303.muffinsmcapi.api.CameraAPI;
import net.firemuffin303.muffinsmcapi.common.data.DripstoneDataManager;
import net.firemuffin303.muffinsmcapi.forge.common.ModEntityTypes;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.firemuffin303.muffinsmcapi.impl.registration.OvenRegistration;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

@Mod(MuffinsMcAPI.MOD_ID)
public class MuffinsOvenAPINeoForge {

    public static Registry<OvenBoatVariant> OVEN_BOAT_VARIANTS_REGISTRY = new RegistryBuilder<>(BoatRegistry.OVEN_BOAT_VARIANT)
            .maxId(2048)
            .create();

    public MuffinsOvenAPINeoForge(IEventBus eventBus){
        OvenRegistration.setPlatformHandler(OvenRegistration::addRegistry);

        MuffinsMcAPI.init();

        NeoForge.EVENT_BUS.addListener(MuffinsOvenAPINeoForge::registerCommand);
        NeoForge.EVENT_BUS.addListener(MuffinsOvenAPINeoForge::onRegisterReloadListeners);
        eventBus.addListener(this::registerPostInit);
        eventBus.addListener(this::customRegistryObject);
        eventBus.addListener(this::registerObject);

        ModEntityTypes.ENTITY_TYPE.register(eventBus);
    }

    public void registerPostInit(FMLCommonSetupEvent event){
    }

    public void registerObject(RegisterEvent event){
        OvenRegistration.RESOURCE_REGISTRIES.forEach(registry -> registerResources(registry,event));
    }

    public void customRegistryObject(NewRegistryEvent newRegistryEvent){
        newRegistryEvent.register(OVEN_BOAT_VARIANTS_REGISTRY);

    }

    private static void registerCommand(RegisterCommandsEvent commandsEvent){
        CameraAPI.registerCommand(commandsEvent.getDispatcher(),commandsEvent.getBuildContext(),commandsEvent.getCommandSelection());
    }

    private static void onRegisterReloadListeners(AddReloadListenerEvent event) {
        HolderLookup.Provider provider = event.getRegistryAccess();
        event.addListener(new DripstoneDataManager(provider));
    }

    private static  <T> void registerResources(ResourceRegistry<T> registry,RegisterEvent event) {
        registry.getValues().forEach((holder,supplier) -> {
            event.register(registry.getResource(),holder.getResourceLocation(), () -> supplier.get());
            holder.createHolder(false);

        });
    }

}
