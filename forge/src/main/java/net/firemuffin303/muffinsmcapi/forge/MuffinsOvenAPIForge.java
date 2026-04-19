package net.firemuffin303.muffinsmcapi.forge;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.api.BlockEntityTypeUtil;
import net.firemuffin303.muffinsmcapi.api.BoatRegistry;
import net.firemuffin303.muffinsmcapi.forge.api.registry.ForgeOvenRegistry;
import net.firemuffin303.muffinsmcapi.forge.common.ModBoatVariants;
import net.firemuffin303.muffinsmcapi.forge.common.ModEntityTypes;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.firemuffin303.muffinsmcapi.impl.registration.OvenRegistration;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = MuffinsMcAPI.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(MuffinsMcAPI.MOD_ID)
public class MuffinsOvenAPIForge {

    public static Supplier<IForgeRegistry<OvenBoatVariant>> OVEN_BOAT_VARIANTS_REGISTRY;

    public MuffinsOvenAPIForge(){
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBoatVariants.OVEN_BOAT_VARIANT.register(eventBus);

        OvenRegistration.setPlatformHandler(OvenRegistration::addRegistry);


        MuffinsMcAPI.init();

        ModEntityTypes.ENTITY_TYPE.register(eventBus);
        eventBus.register(this);
    }

    @SubscribeEvent
    public static void registerPostInit(FMLCommonSetupEvent event){
        event.enqueueWork(BlockEntityTypeUtil::implementBlockEntityType);
    }

    @SubscribeEvent
    public static void registerObject(RegisterEvent event){
        OvenRegistration.RESOURCE_REGISTRIES.forEach(registry -> registerResources(registry,event));
    }

    @SubscribeEvent
    public static void registerNewRegistry(NewRegistryEvent event){
        OVEN_BOAT_VARIANTS_REGISTRY = event.create(new RegistryBuilder<OvenBoatVariant>()
                .setName(BoatRegistry.OVEN_BOAT_VARIANT.location())
                .allowModification()
                .setMaxID(2048));
    }

    private static  <T> void registerResources(ResourceRegistry<T> registry,RegisterEvent event) {
        registry.getValues().forEach(tRegistryHolder -> {
            event.register(registry.getResource(),helper -> helper.register(tRegistryHolder.getResourceLocation(),tRegistryHolder.resolve()));
        });
    }

}
