package net.firemuffin303.muffinsmcapi.impl.entity.boat;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.muffinsmcapi.api.BoatRegistry;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.client.OvenBoatRenderer;
import net.firemuffin303.muffinsmcapi.util.PlatformUtil;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

public class OvenBoatUtil {
    //public static final Supplier<EntityType<OvenBoatEntity>> OVEN_BOAT = PlatformUtil.registerEntityType("oven_boat", EntityType.Builder.of(OvenBoatEntity::new, MobCategory.MISC));
    //public static final Supplier<EntityType<OvenChestBoatEntity>> OVEN_CHEST_BOAT = PlatformUtil.registerEntityType("oven_chest_boat",EntityType.Builder.of(OvenChestBoatEntity::new,MobCategory.MISC));

    public static final EntityDataSerializer<Optional<OvenBoatVariant>> SERIALIZER = EntityDataSerializer.optional((friendlyByteBuf, ovenBoatVariant) -> friendlyByteBuf.writeResourceLocation(OvenBoatUtil.getBoatKey(ovenBoatVariant)), friendlyByteBuf -> OvenBoatUtil.getBoat(friendlyByteBuf.readResourceLocation()));

    public static ModelLayerLocation createChestBoatModelName(ResourceLocation resourceLocation) {
        return new ModelLayerLocation(resourceLocation.withPrefix("chest_boat/"),"main");
    }

    public static ModelLayerLocation createBoatModelName(ResourceLocation resourceLocation) {
        return new ModelLayerLocation(resourceLocation.withPrefix("boat/"),"main");
    }

    @SuppressWarnings("unchecked")
    public static void entityRendererRegister(PlatformUtil.EntityRendererSupplier entityRendererSupplier){
        //entityRendererSupplier.create(OvenBoatUtil.OVEN_BOAT.get(), context -> new OvenBoatRenderer(context,false));
        //entityRendererSupplier.create(OvenBoatUtil.OVEN_CHEST_BOAT.get(),context -> new OvenBoatRenderer(context,true));
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
}
