package net.firemuffin303.muffinsmcapi.client;

import net.firemuffin303.muffinsmcapi.api.CustomEffectRegistry;
import net.firemuffin303.muffinsmcapi.api.ModIntegrationHelper;
import net.firemuffin303.muffinsmcapi.network.CameraShakePacket;
import net.firemuffin303.muffinsmcapi.util.PlatformUtil;

public class MuffinMcAPIClient {
    public static void init(){
        ModIntegrationHelper.register("jei");
        ModIntegrationHelper.register("emi");

        CustomEffectRegistry.init();

        PlatformUtil.registerClientPacket(CameraShakePacket.TYPE, CameraShakePacket.STREAM_CODEC);
    }
}
