package net.firemuffin303.muffinsmcapi.network.customRaid;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.client.MuffinMcAPIClient;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import java.util.HashSet;
import java.util.Set;

public record CustomRaidDebugPacket(Set<BlockPos> customRaids) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<CustomRaidDebugPacket> TYPE = new Type<>(MuffinsMcAPI.modid("custom_raid_debug"));
    public static final StreamCodec<FriendlyByteBuf,CustomRaidDebugPacket> STREAM_CODEC = CustomPacketPayload.codec(CustomRaidDebugPacket::write,CustomRaidDebugPacket::new);

    private CustomRaidDebugPacket(FriendlyByteBuf friendlyByteBuf){
        this((Set<BlockPos>) friendlyByteBuf.readCollection(HashSet::new,BlockPos.STREAM_CODEC));
    }

    private void write(FriendlyByteBuf friendlyByteBuf){
        friendlyByteBuf.writeCollection(this.customRaids,BlockPos.STREAM_CODEC);
    }

    public static void handle(CustomRaidDebugPacket packet){
        MuffinMcAPIClient.CUSTOM_RAID_DEBUG_RENDERER.setRaidCenters(packet.customRaids);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
