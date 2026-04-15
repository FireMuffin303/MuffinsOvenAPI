package net.firemuffin303.muffinsmcapi.impl.entity.boat;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.minecraft.network.syncher.EntityDataSerializer;

import java.util.Optional;

public class OvenBoatUtil {
    public static final EntityDataSerializer<Optional<OvenBoatVariant>> SERIALIZER = EntityDataSerializer.optional((friendlyByteBuf, ovenBoatVariant) -> friendlyByteBuf.writeId(MuffinsMcAPI.OVEN_BOAT_VARIANT_REGISTRY,ovenBoatVariant), friendlyByteBuf -> friendlyByteBuf.readById(MuffinsMcAPI.OVEN_BOAT_VARIANT_REGISTRY));

}
