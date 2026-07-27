package net.firemuffin303.muffinsmcapi.util.neoforge;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.network.PacketDistributor;

public class PlatformUtilImpl {
    public static boolean isFabric() {
        return false;
    }

    public static boolean isNeoforge() {
        return true;
    }

    public static boolean isJEIInstalled() {
        return ModList.get().isLoaded("jei");
    }

    public static boolean isDevelopment() {
        return false;
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

    public static boolean isEMIInstalled() {
        return ModList.get().isLoaded("emi");
    }

    public static ResourceLocation getBlock(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    public static boolean isModInstalled(String id) {
        return ModList.get().isLoaded(id);
    }

    public static void sendServerPacket(ServerPlayer serverPlayer, CustomPacketPayload customPacketPayload){
        PacketDistributor.sendToPlayer(serverPlayer,customPacketPayload);
    }

    public static <T extends CustomPacketPayload> void registerClientPacket(CustomPacketPayload.Type<T> type, StreamCodec<? super FriendlyByteBuf,T> codec) {

    }
}