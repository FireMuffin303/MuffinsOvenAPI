package net.firemuffin303.muffinsmcapi.network;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.api.CameraAPI;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record CameraShakePacket(float shakeIntensity,float shakeReductionRate) implements CustomPacketPayload {
    public static final StreamCodec<FriendlyByteBuf,CameraShakePacket> STREAM_CODEC = CustomPacketPayload.codec(CameraShakePacket::write,CameraShakePacket::new);
    public static final CustomPacketPayload.Type<CameraShakePacket> TYPE = new Type<>(MuffinsMcAPI.modid("camera_shake"));

    private CameraShakePacket(FriendlyByteBuf friendlyByteBuf){
        this(friendlyByteBuf.readFloat(),friendlyByteBuf.readFloat());
    }

    private void write(FriendlyByteBuf friendlyByteBuf){
        friendlyByteBuf.writeFloat(this.shakeIntensity);
        friendlyByteBuf.writeFloat(this.shakeReductionRate);
    }

    public static void handle(CameraShakePacket payload){
        CameraAPI.clientCameraShake(payload.shakeIntensity(),payload.shakeReductionRate());
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
