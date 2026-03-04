package net.firemuffin303.muffinsmcapi.util.neoforge;

import dev.emi.emi.config.EffectLocation;
import dev.emi.emi.config.EmiConfig;
import dev.emi.emi.platform.EmiAgnos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.network.PacketDistributor;

public class PlatformUtilImpl {
    public static boolean isFabric(){
        return false;
    }

    public static boolean isNeoforge(){
        return true;
    }

    public static boolean isJEIInstalled() {
        return ModList.get().isLoaded("jei");
    }

    public static boolean isEMIInstalled() {
        return ModList.get().isLoaded("emi");
    }

    public static void sendServerPacket(ServerPlayer serverPlayer, CustomPacketPayload customPacketPayload){
        PacketDistributor.sendToPlayer(serverPlayer,customPacketPayload);
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

    public static <T extends CustomPacketPayload> void registerClientPacket(CustomPacketPayload.Type<T> type, StreamCodec<? super FriendlyByteBuf,T> codec) {

    }

    public static boolean isDevelopment() {
        return true;
    }
}
