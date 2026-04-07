package net.firemuffin303.muffinsmcapi.forge.client;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.client.MuffinMcAPIClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod(value = MuffinsMcAPI.MOD_ID)
public class MuffinsMCAPIForgeClient {
    public MuffinsMCAPIForgeClient(){
        MuffinMcAPIClient.init();
    }
}
