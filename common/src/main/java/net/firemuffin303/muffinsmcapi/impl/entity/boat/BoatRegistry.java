package net.firemuffin303.muffinsmcapi.impl.entity.boat;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.client.OvenBoatRenderer;
import net.firemuffin303.muffinsmcapi.util.PlatformUtil;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class BoatRegistry {
    /*
    Boat Registration is modified from Terraform Wood API.
    */

    public static final ResourceKey<Registry<OvenBoatVariant>> OVEN_BOAT_VARIANT = ResourceKey.createRegistryKey(MuffinsMcAPI.modid("oven_boat_type"));

    public static final Supplier<EntityType<OvenBoatEntity>> OVEN_BOAT = PlatformUtil.registerEntityType("oven_boat", EntityType.Builder.of(OvenBoatEntity::new, MobCategory.MISC));
    public static final Supplier<EntityType<OvenChestBoatEntity>> OVEN_CHEST_BOAT = PlatformUtil.registerEntityType("oven_chest_boat",EntityType.Builder.of(OvenChestBoatEntity::new,MobCategory.MISC));

    public static void init(){}


    public static ModelLayerLocation createChestBoatModelName(ResourceLocation resourceLocation) {
        return new ModelLayerLocation(resourceLocation.withPrefix("chest_boat/"),"main");
    }

    public static ModelLayerLocation createBoatModelName(ResourceLocation resourceLocation) {
        return new ModelLayerLocation(resourceLocation.withPrefix("boat/"),"main");
    }

    @SuppressWarnings("unchecked")
    public static void entityRendererRegister(PlatformUtil.EntityRendererSupplier entityRendererSupplier){
        entityRendererSupplier.create(OVEN_BOAT.get(), context -> new OvenBoatRenderer(context,false));
        entityRendererSupplier.create(OVEN_CHEST_BOAT.get(),context -> new OvenBoatRenderer(context,true));
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
