package net.firemuffin303.muffinsmcapi.forge.client;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.client.MuffinMcAPIClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = MuffinsMcAPI.MOD_ID,value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
public class MuffinsMCAPIForgeClient {
    public MuffinsMCAPIForgeClient(){
    }

    public static void clientSetup(FMLClientSetupEvent event){
        event.enqueueWork(MuffinMcAPIClient::init);
    }
}
