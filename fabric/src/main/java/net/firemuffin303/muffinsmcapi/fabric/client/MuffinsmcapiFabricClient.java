package net.firemuffin303.muffinsmcapi.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.firemuffin303.muffinsmcapi.client.MuffinMcAPIClient;
import net.firemuffin303.muffinsmcapi.network.camera.CameraShakePacket;

public final class MuffinsmcapiFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MuffinMcAPIClient.init();

        ClientPlayNetworking.registerGlobalReceiver(CameraShakePacket.TYPE,(cameraShakePacket, context) -> CameraShakePacket.handle(cameraShakePacket));
    }
}
