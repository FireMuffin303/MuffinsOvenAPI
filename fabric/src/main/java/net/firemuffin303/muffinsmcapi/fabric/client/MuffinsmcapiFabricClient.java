package net.firemuffin303.muffinsmcapi.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.firemuffin303.muffinsmcapi.client.MuffinMcAPIClient;
import net.firemuffin303.muffinsmcapi.network.camera.CameraShakePacket;
import net.firemuffin303.muffinsmcapi.network.customRaid.CustomRaidDebugPacket;
import net.minecraft.world.phys.Vec3;

import static net.firemuffin303.muffinsmcapi.client.MuffinMcAPIClient.CUSTOM_RAID_DEBUG_RENDERER;

public final class MuffinsmcapiFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MuffinMcAPIClient.init();

        ClientPlayNetworking.registerGlobalReceiver(CameraShakePacket.TYPE,(cameraShakePacket, context) -> CameraShakePacket.handle(cameraShakePacket));
        ClientPlayNetworking.registerGlobalReceiver(CustomRaidDebugPacket.TYPE,(packet, context) -> CustomRaidDebugPacket.handle(packet));


        WorldRenderEvents.BEFORE_DEBUG_RENDER.register(new WorldRenderEvents.DebugRender() {
            @Override
            public void beforeDebugRender(WorldRenderContext worldRenderContext) {
                Vec3 vec3 = worldRenderContext.camera().getPosition();
                CUSTOM_RAID_DEBUG_RENDERER.render(worldRenderContext.matrixStack(),worldRenderContext.consumers(),vec3.x,vec3.y,vec3.z);
            }
        });

    }
}
