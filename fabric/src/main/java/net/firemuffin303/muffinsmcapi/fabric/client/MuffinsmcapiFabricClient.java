package net.firemuffin303.muffinsmcapi.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.api.CustomEffectRegistry;
import net.firemuffin303.muffinsmcapi.client.MuffinMcAPIClient;
import net.firemuffin303.muffinsmcapi.impl.customEffect.CustomEffectRenderer;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.BoatRegistry;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.client.OvenBoatRenderer;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ChestRaftModel;
import net.minecraft.client.model.RaftModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public final class MuffinsmcapiFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MuffinMcAPIClient.init();
        BoatRegistry.entityRendererRegister(EntityRendererRegistry::register);

        EntityModelLayerRegistry.registerModelLayer(BoatRegistry.createBoatModelName(MuffinsMcAPI.modid("oven")), BoatModel::createBodyModel);
        EntityModelLayerRegistry.registerModelLayer(BoatRegistry.createChestBoatModelName(MuffinsMcAPI.modid("oven")), ChestBoatModel::createBodyModel);
        EntityModelLayerRegistry.registerModelLayer(BoatRegistry.createBoatModelName(MuffinsMcAPI.modid("raft_oven")), RaftModel::createBodyModel);
        EntityModelLayerRegistry.registerModelLayer(BoatRegistry.createChestBoatModelName(MuffinsMcAPI.modid("raft_oven")), ChestRaftModel::createBodyModel);
    }
}
