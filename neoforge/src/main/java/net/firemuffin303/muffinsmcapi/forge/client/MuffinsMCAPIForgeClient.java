package net.firemuffin303.muffinsmcapi.forge.client;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.client.MuffinMcAPIClient;
import net.firemuffin303.muffinsmcapi.forge.MuffinsOvenAPINeoForge;
import net.firemuffin303.muffinsmcapi.network.CameraShakePacket;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ChestRaftModel;
import net.minecraft.client.model.RaftModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.MainThreadPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@Mod(value = MuffinsMcAPI.MOD_ID,dist = Dist.CLIENT)
public class MuffinsMCAPIForgeClient {
    public MuffinsMCAPIForgeClient(IEventBus eventBus){
        MuffinMcAPIClient.init();

        eventBus.addListener(this::registerEntityRender);
        eventBus.addListener(this::registerPacket);
    }


    public void registerEntityRender(EntityRenderersEvent.RegisterLayerDefinitions event){
        MuffinsOvenAPINeoForge.OVEN_BOAT_VARIANTS_REGISTRY.entrySet().forEach(resourceKeyOvenBoatVariantEntry -> {
            ResourceLocation id = resourceKeyOvenBoatVariantEntry.getKey().location();
            boolean isRaft = resourceKeyOvenBoatVariantEntry.getValue().raft();
            event.registerLayerDefinition(new ModelLayerLocation(id.withPrefix("boat/"),"main"),isRaft ? RaftModel::createBodyModel : BoatModel::createBodyModel);
            event.registerLayerDefinition(new ModelLayerLocation(id.withPrefix("chest_boat/"),"main"),isRaft ? ChestRaftModel::createBodyModel : ChestBoatModel::createBodyModel);
        });
    }

    private void registerPacket(RegisterPayloadHandlersEvent event){
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playToClient(CameraShakePacket.TYPE,CameraShakePacket.STREAM_CODEC,new MainThreadPayloadHandler<>((arg, iPayloadContext) -> CameraShakePacket.handle(arg)));
        //registrar.playToClient(CustomRaidDebugPacket.TYPE,CustomRaidDebugPacket.STREAM_CODEC,new MainThreadPayloadHandler<>((arg, iPayloadContext) -> CustomRaidDebugPacket.handle(arg)));
    }
}
