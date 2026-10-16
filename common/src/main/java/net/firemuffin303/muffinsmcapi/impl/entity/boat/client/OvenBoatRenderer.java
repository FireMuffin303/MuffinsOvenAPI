package net.firemuffin303.muffinsmcapi.impl.entity.boat.client;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.firemuffin303.muffinsmcapi.api.BoatRegistry;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.IOvenBoat;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatUtil;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;

import java.util.HashMap;
import java.util.Map;

public class OvenBoatRenderer {
    private final Map<OvenBoatVariant, Pair<ResourceLocation, ListModel<Boat>>> boatResources = new HashMap<>();
    private final EntityRendererProvider.Context context;
    private final boolean isChest;


    public OvenBoatRenderer(EntityRendererProvider.Context context, boolean bl) {
        this.context = context;
        this.isChest = bl;
    }

    public ResourceLocation getTextureLocation(IOvenBoat entity) {
        if(!this.boatResources.containsKey(entity.getOvenBoatVariant().get())){
            this.createModelPair(entity.getOvenBoatVariant().get());
        }
        return (ResourceLocation)((Pair)this.boatResources.get(entity.getOvenBoatVariant().get())).getFirst();
    }

    public Pair<ResourceLocation, ListModel<Boat>> getTextureAndModel(OvenBoatVariant variant) {
        if(!this.boatResources.containsKey(variant)){
            this.createModelPair(variant);
        }

        return this.boatResources.get(variant);
    }

    public boolean hasBoatVariantData(ResourceLocation resourceLocation){
        return BoatRegistry.OVEN_BOAT_REGISTRY.containsKey(resourceLocation);
    }


    public ListModel<Boat> createBoatModel(EntityRendererProvider.Context context,boolean raft,boolean chest,ResourceLocation resourceLocation){
        ModelLayerLocation modelLayerLocation = chest ? OvenBoatUtil.createChestBoatModelName(resourceLocation) : OvenBoatUtil.createBoatModelName(resourceLocation);
        ModelPart modelPart = context.bakeLayer(modelLayerLocation);

        if(raft){
            return chest ? new ChestRaftModel(modelPart) : new RaftModel(modelPart);
        }

        return chest ? new ChestBoatModel(modelPart) : new BoatModel(modelPart);
    }


    public void createModelPair(OvenBoatVariant variant){
        ResourceLocation resourceLocation = BoatRegistry.OVEN_BOAT_REGISTRY.getKey(variant);
        String id = this.isChest ? "textures/entity/chest_boat/" + resourceLocation.getPath() + ".png" :
                "textures/entity/boat/" + resourceLocation.getPath() + ".png";

        this.boatResources.put(variant,
                Pair.of(ResourceLocation.fromNamespaceAndPath(resourceLocation.getNamespace(),id),
                        this.createBoatModel(context, variant.raft(), this.isChest,resourceLocation)
                ));
    }
}
