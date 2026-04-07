package net.firemuffin303.muffinsmcapi.api.customRaid.entity;

import net.firemuffin303.muffinsmcapi.impl.customRaid.CustomRaid;
import net.minecraft.nbt.CompoundTag;

public interface IRaider {

    void registerRaidGoals();

    void setWave(int wave);

    int getWave();

    CustomRaid getRaid();

    default void addAdditionalSaveData(CompoundTag compoundTag){
        compoundTag.putInt("wave",this.getWave());
        if(this.getRaid() != null){
            compoundTag.putInt("raid_id",this.getRaid().getId());
        }


    }
}
