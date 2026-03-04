package net.firemuffin303.muffinsmcapi.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.firemuffin303.muffinsmcapi.client.MuffinMcAPIClient;
import net.firemuffin303.muffinsmcapi.network.camera.CameraShakePacket;
import net.firemuffin303.muffinsmcapi.network.customRaid.CustomRaidDebugPacket;

public final class MuffinsmcapiFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MuffinMcAPIClient.init();

        ClientPlayNetworking.registerGlobalReceiver(CameraShakePacket.TYPE,(cameraShakePacket, context) -> CameraShakePacket.handle(cameraShakePacket));
        ClientPlayNetworking.registerGlobalReceiver(CustomRaidDebugPacket.TYPE,(packet, context) -> CustomRaidDebugPacket.handle(packet));

    }
}
