package net.firemuffin303.muffinsmcapi.impl.entity.boat.client;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.BoatRegistry;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.IOvenBoat;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;

import java.util.Map;

public class OvenBoatRenderer extends BoatRenderer {
    private final Map<OvenBoatVariant, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    public OvenBoatRenderer(EntityRendererProvider.Context context, boolean bl) {
        super(context, bl);

        this.boatResources = BoatRegistry.OVEN_BOAT_VARIANT_REGISTRY.entrySet().stream().collect(ImmutableMap.toImmutableMap(Map.Entry::getValue,(entry) -> {
            OvenBoatVariant ovenBoatVariant = entry.getValue();
            ResourceLocation resourceLocation = entry.getKey().location();
            String id = bl ? "textures/entity/chest_boat/" + resourceLocation.getPath() + ".png" : "textures/entity/boat/" + resourceLocation.getPath() + ".png";
            return Pair.of(new ResourceLocation(resourceLocation.getNamespace(),id),this.createBoatModel(context, ovenBoatVariant.raft(), bl,resourceLocation));
        }));
    }

    public ResourceLocation getTextureLocation(Boat entity) {
        if (entity instanceof IOvenBoat ovenBoat) {
            return (ResourceLocation)((Pair)this.boatResources.get(ovenBoat.getOvenBoatVariant().get())).getFirst();
        } else {
            return super.getTextureLocation(entity);
        }
    }

    public Pair<ResourceLocation, ListModel<Boat>> getTextureAndModel(OvenBoatVariant variant) {
        return this.boatResources.get(variant);
    }


    public ListModel<Boat> createBoatModel(EntityRendererProvider.Context context,boolean raft,boolean chest,ResourceLocation resourceLocation){
        ModelLayerLocation modelLayerLocation = chest ? BoatRegistry.createChestBoatModelName(resourceLocation) : BoatRegistry.createBoatModelName(resourceLocation);
        ModelPart modelPart = context.bakeLayer(modelLayerLocation);

        if(raft){
            return chest ? new ChestRaftModel(modelPart) : new RaftModel(modelPart);
        }

        return chest ? new ChestBoatModel(modelPart) : new BoatModel(modelPart);
    }
}
