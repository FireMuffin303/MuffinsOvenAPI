package net.firemuffin303.muffinsmcapi.forge.client;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.client.MuffinMcAPIClient;
import net.firemuffin303.muffinsmcapi.forge.common.ModEntityTypes;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.BoatRegistry;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = MuffinsMcAPI.MOD_ID,value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
public class MuffinsMCAPIForgeClient {
    public MuffinsMCAPIForgeClient(){
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event){
        event.enqueueWork(MuffinMcAPIClient::init);
    }

    @SubscribeEvent
    public static void registerEntityRender(EntityRenderersEvent.RegisterRenderers event){
        BoatRegistry.entityRendererRegister(event::registerEntityRenderer);
    }

    @SubscribeEvent
    public static void registerEntityRender(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(BoatRegistry.createBoatModelName(MuffinsMcAPI.modid("oven")), BoatModel::createBodyModel);
        event.registerLayerDefinition(BoatRegistry.createChestBoatModelName(MuffinsMcAPI.modid("oven")), ChestBoatModel::createBodyModel);
    }
}
