package net.firemuffin303.muffinsmcapi.impl.customRaid;

import com.mojang.logging.LogUtils;
import net.firemuffin303.muffinsmcapi.network.customRaid.CustomRaidDebugPacket;
import net.firemuffin303.muffinsmcapi.util.PlatformUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomRaidManager extends SavedData {

    public final Map<Integer,CustomRaid> raidMap = new HashMap<>();
    private final ServerLevel level;
    private int nextAvailableID;
    private int tick;

    public static SavedData.Factory<CustomRaidManager> factory(ServerLevel serverLevel){
        return new Factory<>(() -> new CustomRaidManager(serverLevel),(compoundTag, provider) -> load(serverLevel,compoundTag), null);
    }

    public CustomRaidManager(ServerLevel serverLevel){
        this.level = serverLevel;
        this.nextAvailableID = 1;
        this.setDirty();
    }


    public static CustomRaidManager load(ServerLevel serverLevel, CompoundTag compoundTag){
        CustomRaidManager customRaids = new CustomRaidManager(serverLevel);
        ListTag listTag = compoundTag.getList("Raids", 10);

        for (int i = 0; i < listTag.size(); i++) {
            CompoundTag compoundTag2 = listTag.getCompound(i);
            CustomRaid raid = new CustomRaid(serverLevel, compoundTag2);
            customRaids.raidMap.put(raid.getId(), raid);
        }

        return customRaids;
    }

    public void tick(){
        ++this.tick;
        Iterator<CustomRaid> customRaidIterator = this.raidMap.values().iterator();

        while (customRaidIterator.hasNext()){
            CustomRaid customRaid = customRaidIterator.next();
            if(this.level.getGameRules().getBoolean(GameRules.RULE_DISABLE_RAIDS)){
                customRaid.stop();
            }

            if(customRaid.isStopped()){
                customRaidIterator.remove();
                this.setDirty();
            }else{
                customRaid.tick();
            }
        }

        if(this.tick % 200 == 0){
            this.setDirty();
        }


        if(PlatformUtil.isDevelopment()){
            List<ServerPlayer> playerList = level.players();
            playerList.forEach(serverPlayer -> {
                PlatformUtil.sendServerPacket(serverPlayer,new CustomRaidDebugPacket(this.raidMap.values().stream().map(CustomRaid::getCenter).collect(Collectors.toSet())));
            });
        }
    }


    public CustomRaid getNearbyRaid(BlockPos blockPos,int i){
        CustomRaid customRaid = null;
        double d = i;
        for (CustomRaid customRaid1 : this.raidMap.values()) {
            double centerDistance = customRaid1.getCenter().distSqr(blockPos);
            if (customRaid1.isActive() && centerDistance < d) {
                customRaid = customRaid1;
                d = centerDistance;
            }
        }

        return customRaid;
    }

    public boolean isRaided(BlockPos blockPos){
        return this.getNearbyRaid(blockPos,9216) != null;
    }

    @Override
    public CompoundTag save(CompoundTag compoundTag, HolderLookup.Provider provider) {
        compoundTag.putInt("NextAvailableID", this.nextAvailableID);
        compoundTag.putInt("Tick", this.tick);
        ListTag listTag = new ListTag();

        for (CustomRaid raid : this.raidMap.values()) {
            CompoundTag compoundTag2 = new CompoundTag();
            raid.save(compoundTag2);
            listTag.add(compoundTag2);
        }

        compoundTag.put("Raids", listTag);
        return compoundTag;
    }

    public CustomRaid createOrExtendRaid(ResourceLocation resourceLocation, ServerPlayer serverPlayer, BlockPos blockPos){
        if(serverPlayer.isSpectator()){
            return null;
        } else if(this.level.getGameRules().getBoolean(GameRules.RULE_DISABLE_RAIDS)){
            return null;
        }

        DimensionType dimensionType = serverPlayer.level().dimensionType();
        if(!dimensionType.hasRaids()){
            return null;
        }
        CustomRaid customRaid = this.getOrCreateRaid(resourceLocation.toString(),serverPlayer.serverLevel(),blockPos);
        if(!this.raidMap.containsKey(customRaid.getId())){
            this.raidMap.put(customRaid.getId(),customRaid);
        }
        this.setDirty();
        return customRaid;

    }

    private CustomRaid getOrCreateRaid(String raidId,ServerLevel serverLevel, BlockPos blockPos) {
        CustomRaid raid = this.getNearbyRaid(blockPos,9216);
        return raid != null ? raid : new CustomRaid(this.getUniqueId(),raidId, serverLevel, blockPos);
    }

    private int getUniqueId() {
        return ++this.nextAvailableID;
    }
}
