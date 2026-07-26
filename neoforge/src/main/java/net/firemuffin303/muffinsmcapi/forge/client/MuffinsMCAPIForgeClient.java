package net.firemuffin303.muffinsmcapi.forge.client;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.client.MuffinMcAPIClient;
import net.firemuffin303.muffinsmcapi.forge.MuffinsOvenAPIForge;
import net.firemuffin303.muffinsmcapi.forge.api.registry.ForgeOvenRegistry;
import net.firemuffin303.muffinsmcapi.forge.common.ModBoatVariants;
import net.firemuffin303.muffinsmcapi.api.BoatRegistry;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatUtil;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ChestRaftModel;
import net.minecraft.client.model.RaftModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = MuffinsMcAPI.MOD_ID,value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
public class MuffinsMCAPIForgeClient {
    public MuffinsMCAPIForgeClient(){
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event){
        event.enqueueWork(MuffinMcAPIClient::init);
    }


    @SubscribeEvent
    public static void registerEntityRender(EntityRenderersEvent.RegisterLayerDefinitions event){
        MuffinsOvenAPIForge.OVEN_BOAT_VARIANTS_REGISTRY.get().getEntries().forEach(resourceKeyOvenBoatVariantEntry -> {
            ResourceLocation id = resourceKeyOvenBoatVariantEntry.getKey().location();
            boolean isRaft = resourceKeyOvenBoatVariantEntry.getValue().raft();
            event.registerLayerDefinition(new ModelLayerLocation(id.withPrefix("boat/"),"main"),isRaft ? RaftModel::createBodyModel : BoatModel::createBodyModel);
            event.registerLayerDefinition(new ModelLayerLocation(id.withPrefix("chest_boat/"),"main"),isRaft ? ChestRaftModel::createBodyModel : ChestBoatModel::createBodyModel);
        });
    }
}
