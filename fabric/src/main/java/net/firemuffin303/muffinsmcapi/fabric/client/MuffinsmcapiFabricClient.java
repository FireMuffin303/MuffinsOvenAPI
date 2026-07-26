package net.firemuffin303.muffinsmcapi.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.firemuffin303.muffinsmcapi.client.MuffinMcAPIClient;
import net.firemuffin303.muffinsmcapi.fabric.api.FabricOvenRegistry;
import net.firemuffin303.muffinsmcapi.network.CameraShakePacket;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ChestRaftModel;
import net.minecraft.client.model.RaftModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public final class MuffinsmcapiFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MuffinMcAPIClient.init();

        ClientPlayNetworking.registerGlobalReceiver(CameraShakePacket.TYPE,(cameraShakePacket, context) -> CameraShakePacket.handle(cameraShakePacket));

        FabricOvenRegistry.OVEN_BOAT_VARIANT_REGISTRY.entrySet().forEach(resourceKeyOvenBoatVariantEntry -> {
            ResourceLocation id = resourceKeyOvenBoatVariantEntry.getKey().location();
            boolean isRaft = resourceKeyOvenBoatVariantEntry.getValue().raft();
            EntityModelLayerRegistry.registerModelLayer(new ModelLayerLocation(id.withPrefix("boat/"),"main"),isRaft ?  RaftModel::createBodyModel : BoatModel::createBodyModel);
            EntityModelLayerRegistry.registerModelLayer(new ModelLayerLocation(id.withPrefix("chest_boat/"),"main"),isRaft ?  ChestRaftModel::createBodyModel : ChestBoatModel::createBodyModel);
        });
    }
}
