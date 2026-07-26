package net.firemuffin303.muffinsmcapi.util.fabric;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.fabric.MuffinsmcapiFabric;
import net.firemuffin303.muffinsmcapi.fabric.api.FabricOvenRegistry;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.firemuffin303.muffinsmcapi.network.CameraShakePacket;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class PlatformUtilImpl {
    public static boolean isFabric(){
        return true;
    }

    public static boolean isNeoforge() {
        return false;
    }

    public static boolean isJEIInstalled() {
        return FabricLoader.getInstance().isModLoaded("jei");
    }

    public static boolean isEMIInstalled() {
        return FabricLoader.getInstance().isModLoaded("emi");
    }

    /*
    public static boolean emiEffectLocationHidden() {
        return EmiConfig.effectLocation == EffectLocation.HIDDEN;
    }

    public static boolean emiEffectRenderTop() {
        return EmiConfig.effectLocation == EffectLocation.TOP;
    }

    public static boolean emiEffectIsCompressed() {
        return EmiConfig.effectLocation == EffectLocation.LEFT_COMPRESSED || EmiConfig.effectLocation == EffectLocation.RIGHT_COMPRESSED;
    }

    public static boolean emiAgnosInventoryTabs() {
        return EmiAgnos.isModLoaded("inventorytabs");
    }

    public static boolean emiAgnosIsForge() {
        return EmiAgnos.isForge();
    }
    */

    public static boolean isDevelopment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    public static ResourceLocation getBlock(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }




    public static <T extends Entity>  Supplier<EntityType<T>> registerEntityType(String id, EntityType.Builder<T> entityType) {
        EntityType<T> entityType1 = Registry.register(BuiltInRegistries.ENTITY_TYPE,MuffinsMcAPI.modid(id),entityType.sized(1.375F, 0.5625F).clientTrackingRange(10).build(id));
        return () -> entityType1;
    }

    public static boolean isModInstalled(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    public static void sendServerPacket(ServerPlayer serverPlayer, CustomPacketPayload customPacketPayload) {
        ServerPlayNetworking.send(serverPlayer,customPacketPayload);
    }

    public static void registerClientPacket(CustomPacketPayload.Type<CameraShakePacket> type, StreamCodec<FriendlyByteBuf, CameraShakePacket> codec) {
        PayloadTypeRegistry.playS2C().register(type,codec);
    }
}
