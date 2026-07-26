package net.firemuffin303.muffinsmcapi.impl.entity.boat.client;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.IOvenBoat;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatUtil;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;

import java.util.Map;

public class OvenBoatRenderer {
    private final Map<OvenBoatVariant, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    public OvenBoatRenderer(EntityRendererProvider.Context context, boolean bl) {
        this.boatResources = OvenBoatUtil.entrySet().stream().collect(ImmutableMap.toImmutableMap(Map.Entry::getValue,(entry) -> {
            OvenBoatVariant ovenBoatVariant = entry.getValue();
            ResourceLocation resourceLocation = entry.getKey().location();
            String id = bl ? "textures/entity/chest_boat/" + resourceLocation.getPath() + ".png" : "textures/entity/boat/" + resourceLocation.getPath() + ".png";
            return Pair.of(ResourceLocation.fromNamespaceAndPath(resourceLocation.getNamespace(),id),this.createBoatModel(context, ovenBoatVariant.raft(), bl,resourceLocation));
        }));
    }

    public ResourceLocation getTextureLocation(IOvenBoat entity) {
        return (ResourceLocation)((Pair)this.boatResources.get(entity.getOvenBoatVariant().get())).getFirst();
    }

    public Pair<ResourceLocation, ListModel<Boat>> getTextureAndModel(OvenBoatVariant variant) {
        return this.boatResources.get(variant);
    }

    public boolean hasBoatVariantData(ResourceLocation resourceLocation){
        return OvenBoatUtil.hasBoat(resourceLocation);
    }


    public ListModel<Boat> createBoatModel(EntityRendererProvider.Context context,boolean raft,boolean chest,ResourceLocation resourceLocation){
        ModelLayerLocation modelLayerLocation = chest ? OvenBoatUtil.createChestBoatModelName(resourceLocation) : OvenBoatUtil.createBoatModelName(resourceLocation);
        ModelPart modelPart = context.bakeLayer(modelLayerLocation);

        if(raft){
            return chest ? new ChestRaftModel(modelPart) : new RaftModel(modelPart);
        }

        return chest ? new ChestBoatModel(modelPart) : new BoatModel(modelPart);
    }
}
