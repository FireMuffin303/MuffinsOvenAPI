package net.firemuffin303.muffinsmcapi.impl.customRaid;

import com.google.common.collect.Sets;
import com.mojang.logging.LogUtils;
import net.firemuffin303.muffinsmcapi.common.data.RaidDataManager;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class CustomRaid {
    private final int id;
    private final String raidID;
    private int raidLevel;
    private final boolean started;
    private boolean active;
    private long tickActive;

    private final ServerLevel level;
    private BlockPos center;
    private Optional<BlockPos> waveSpawnPos;
    private final ServerBossEvent raidEvent;
    private RaidStatus status;

    public CustomRaid(int id,String raidID,ServerLevel serverLevel,BlockPos center){
        this.id = id;
        this.raidID = raidID;
        this.started = false;
        this.raidEvent = new ServerBossEvent(Component.empty(), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.NOTCHED_10);
        this.raidEvent.setProgress(0.0f);
        this.level = serverLevel;
        this.center = center;
        this.active = true;
        this.status = RaidStatus.ONGOING;
    }

    public CustomRaid(ServerLevel serverLevel, CompoundTag compoundTag){
        this.level = serverLevel;
        this.id = compoundTag.getInt("id");
        this.raidID = compoundTag.getString("raid_id");
        this.started = compoundTag.getBoolean("started");
        this.active = compoundTag.getBoolean("active");
        this.status = RaidStatus.getByName(compoundTag.getString("status"));
        this.raidEvent = new ServerBossEvent(Component.empty(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.NOTCHED_10);
        this.center = new BlockPos(compoundTag.getInt("center_x"),compoundTag.getInt("center_y"),compoundTag.getInt("center_z"));
    }

    public CompoundTag save(CompoundTag compoundTag){
        compoundTag.putInt("id",this.id);
        compoundTag.putString("raid_id",this.raidID);
        compoundTag.putBoolean("started",this.started);
        compoundTag.putBoolean("active",this.active);
        compoundTag.putLong("tick_active",this.tickActive);

        compoundTag.putString("status",this.status.getName());

        compoundTag.putInt("center_x",this.center.getX());
        compoundTag.putInt("center_y",this.center.getY());
        compoundTag.putInt("center_z",this.center.getZ());

        return compoundTag;
    }

    public void setRaidLevel(int raidLevel) {
        this.raidLevel = raidLevel;
    }

    public void stop(){
        this.active = false;
        this.raidEvent.removeAllPlayers();
        this.status = RaidStatus.STOPPED;

    }

    public void tick(){
        if(!this.isStopped()){
            if(this.status == RaidStatus.ONGOING){
                boolean bl = this.active;
                this.active = this.level.hasChunkAt(this.center);
                if(this.level.getDifficulty() == Difficulty.PEACEFUL){
                    this.stop();
                    return;
                }

                if (bl != this.active) {
                    this.raidEvent.setVisible(this.active);
                }
            }

            if(this.tickActive % 20 == 0){
                this.updatePlayers();
            }
        }
    }


    private void updatePlayers() {
        Set<ServerPlayer> set = Sets.newHashSet(this.raidEvent.getPlayers());
        List<ServerPlayer> list = this.level.getPlayers(this.validPlayer());

        for (ServerPlayer serverPlayer : list) {
            if (!set.contains(serverPlayer)) {
                this.raidEvent.addPlayer(serverPlayer);
            }
        }

        for (ServerPlayer serverPlayerx : set) {
            if (!list.contains(serverPlayerx)) {
                this.raidEvent.removePlayer(serverPlayerx);
            }
        }
    }

    private Predicate<ServerPlayer> validPlayer() {
        return serverPlayer -> {
            BlockPos blockPos = serverPlayer.blockPosition();
            return serverPlayer.isAlive() && getCustomRaidManager().getNearbyRaid(blockPos,9216) == this;
        };
    }

    private CustomRaidManager getCustomRaidManager(){
        return this.level.getDataStorage().computeIfAbsent(CustomRaidManager.factory(this.level),"custom_raid");
    }

    public int getId() {
        return id;
    }

    public String getRaidID() {
        return raidID;
    }

    public ServerLevel getLevel() {
        return level;
    }

    public BlockPos getCenter() {
        return center;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isStopped(){
        return this.status == RaidStatus.STOPPED;
    }


    public enum RaidStatus{
        ONGOING,
        VICTORY,
        LOSS,
        STOPPED;

        private static final RaidStatus[] VALUES = values();

        public String getName() {
            return this.name().toLowerCase(Locale.ROOT);
        }

        static RaidStatus getByName(String string) {
            for (RaidStatus raidStatus : VALUES) {
                if (string.equalsIgnoreCase(raidStatus.name())) {
                    return raidStatus;
                }
            }

            return ONGOING;
        }
    }
}
