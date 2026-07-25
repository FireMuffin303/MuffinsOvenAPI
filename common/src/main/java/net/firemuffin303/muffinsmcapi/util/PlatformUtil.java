package net.firemuffin303.muffinsmcapi.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class PlatformUtil {

    @ExpectPlatform
    public static boolean isFabric(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isNeoforge(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isModInstalled(String id){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isJEIInstalled(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isEMIInstalled(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isDevelopment(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean emiEffectLocationHidden(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean emiEffectRenderTop(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean emiEffectIsCompressed(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean emiAgnosInventoryTabs(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean emiAgnosIsForge(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static ResourceLocation getBlock(Block block){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends Entity> Supplier<EntityType<T>> registerEntityType(String id, EntityType.Builder<T> entityType){
        throw new AssertionError();
    }

    @FunctionalInterface
    public interface EntityRendererSupplier<T extends Entity>{
        void create(EntityType<? extends Entity> entityType, EntityRendererProvider<T> entityRendererProvider);
    }
}
