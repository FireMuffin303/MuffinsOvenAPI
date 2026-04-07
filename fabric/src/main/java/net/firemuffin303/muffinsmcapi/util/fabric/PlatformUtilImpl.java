package net.firemuffin303.muffinsmcapi.util.fabric;

import dev.emi.emi.config.EffectLocation;
import dev.emi.emi.config.EmiConfig;
import dev.emi.emi.platform.EmiAgnos;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.client.renderer.CustomRaidDebugRenderer;
import net.firemuffin303.muffinsmcapi.fabric.client.MuffinsmcapiFabricClient;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

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

    public static void sendServerPacket(ServerPlayer serverPlayer, CustomPacketPayload customPacketPayload) {
        ServerPlayNetworking.send(serverPlayer,customPacketPayload);
    }

    public static <T extends CustomPacketPayload> void registerClientPacket(CustomPacketPayload.Type<T> type, StreamCodec<? super FriendlyByteBuf,T> codec) {
        PayloadTypeRegistry.playS2C().register(type,codec);
    }

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

    public static boolean isDevelopment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    public static <T> DataComponentType<T> registerDataComponent(String id, DataComponentType.Builder<T> builder) {
        DataComponentType<T> dataComponentType = Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, ResourceLocation.fromNamespaceAndPath(MuffinsMcAPI.MOD_ID,id),builder.build());
        return dataComponentType;
    }


}
