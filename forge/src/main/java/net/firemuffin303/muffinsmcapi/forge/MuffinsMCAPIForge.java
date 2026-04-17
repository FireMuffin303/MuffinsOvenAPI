package net.firemuffin303.muffinsmcapi.forge;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.api.BlockEntityTypeUtil;
import net.firemuffin303.muffinsmcapi.forge.common.ModEntityTypes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod.EventBusSubscriber(modid = MuffinsMcAPI.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(MuffinsMcAPI.MOD_ID)
public class MuffinsMCAPIForge {

    public MuffinsMCAPIForge(){
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MuffinsMcAPI.init();

        ModEntityTypes.ENTITY_TYPE.register(eventBus);
        eventBus.register(this);
    }

    @SubscribeEvent
    public static void registerPostInit(FMLCommonSetupEvent event){
        event.enqueueWork(BlockEntityTypeUtil::implementBlockEntityType);
    }
}
