package net.firemuffin303.muffinsmcapi.fabric.client.datagen;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.api.customRaid.APIRaidDataProvider;
import net.firemuffin303.muffinsmcapi.impl.customRaid.common.CustomRaidData;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RaidProvider extends APIRaidDataProvider {
    public RaidProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(packOutput, completableFuture);
    }

    @Override
    public void generateRaidData(HolderLookup.Provider provider, RaidDataOutput raidDataBuilder) {
        raidDataBuilder.add(MuffinsMcAPI.modid("night_raid"), new CustomRaidData(MobEffects.TRIAL_OMEN.value(), List.of(new CustomRaidData.RaiderData(EntityType.ZOMBIE,List.of(0,2,3,4,5,6,7,8)))));
    }
}
