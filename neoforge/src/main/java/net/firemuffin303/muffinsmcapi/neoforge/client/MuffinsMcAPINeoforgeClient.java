package net.firemuffin303.muffinsmcapi.neoforge.client;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.client.MuffinMcAPIClient;
import net.firemuffin303.muffinsmcapi.network.camera.CameraShakePacket;
import net.firemuffin303.muffinsmcapi.network.customRaid.CustomRaidDebugPacket;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.MainThreadPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@Mod(value = MuffinsMcAPI.MOD_ID,dist = Dist.CLIENT)
public class MuffinsMcAPINeoforgeClient {

    public MuffinsMcAPINeoforgeClient(IEventBus eventBus){
        MuffinMcAPIClient.init();
        eventBus.addListener(MuffinsMcAPINeoforgeClient::registerPacket);
    }

    private static void registerPacket(RegisterPayloadHandlersEvent event){
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playToClient(CameraShakePacket.TYPE,CameraShakePacket.STREAM_CODEC,new MainThreadPayloadHandler<>((arg, iPayloadContext) -> CameraShakePacket.handle(arg)));
        registrar.playToClient(CustomRaidDebugPacket.TYPE,CustomRaidDebugPacket.STREAM_CODEC,new MainThreadPayloadHandler<>((arg, iPayloadContext) -> CustomRaidDebugPacket.handle(arg)));
    }
}
