package net.firemuffin303.muffinsmcapi.impl.entity.boat;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class OvenBoatUtil {

    public static final EntityDataSerializer<Optional<OvenBoatVariant>> SERIALIZER = EntityDataSerializer.optional((friendlyByteBuf, ovenBoatVariant) -> friendlyByteBuf.writeResourceLocation(OvenBoatUtil.getBoatKey(ovenBoatVariant)), friendlyByteBuf -> OvenBoatUtil.getBoat(friendlyByteBuf.readResourceLocation()));

    public static ModelLayerLocation createChestBoatModelName(ResourceLocation resourceLocation) {
        return new ModelLayerLocation(resourceLocation.withPrefix("chest_boat/"),"main");
    }

    public static ModelLayerLocation createBoatModelName(ResourceLocation resourceLocation) {
        return new ModelLayerLocation(resourceLocation.withPrefix("boat/"),"main");
    }

    @ExpectPlatform
    public static ResourceLocation getBoatKey(OvenBoatVariant ovenBoatVariant){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static OvenBoatVariant getBoat(ResourceLocation resourceLocation){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Set<Map.Entry<ResourceKey<OvenBoatVariant>, OvenBoatVariant>> entrySet(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean hasBoat(ResourceLocation resourceLocation){
        throw new AssertionError();
    }
}
