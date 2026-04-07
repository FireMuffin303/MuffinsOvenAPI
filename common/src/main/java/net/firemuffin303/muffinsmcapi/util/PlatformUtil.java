package net.firemuffin303.muffinsmcapi.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.muffinsmcapi.client.renderer.CustomRaidDebugRenderer;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

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
    public static boolean isJEIInstalled(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isEMIInstalled(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void sendServerPacket(ServerPlayer serverPlayer, CustomPacketPayload customPacketPayload){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends CustomPacketPayload> void registerClientPacket(CustomPacketPayload.Type<T> type, StreamCodec<? super FriendlyByteBuf,T> codec){
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
    public static <T> DataComponentType<T> registerDataComponent(String id, DataComponentType.Builder<T> builder){
        throw new AssertionError();
    }


    public  interface PackerProvider{
        <T extends CustomPacketPayload> void create(CustomPacketPayload.Type<T> type, StreamCodec<? super FriendlyByteBuf,T> codec);
    }
}
