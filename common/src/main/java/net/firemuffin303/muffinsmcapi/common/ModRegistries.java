package net.firemuffin303.muffinsmcapi.common;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.impl.dripstone.data.BlockFluidInfo;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class ModRegistries {
    //public static final ResourceKey<Registry<CustomRaidData>> RAID_DATA = ResourceKey.createRegistryKey(MuffinsMcAPI.modid("raid_data"));
    public static final ResourceKey<Registry<BlockFluidInfo>> BLOCK_FLUID_INFO = ResourceKey.createRegistryKey(MuffinsMcAPI.modid("dripstone/block_fluid_info"));
    public static final ResourceKey<Registry<BlockFluidInfo>> FLUID_CHANCE_INFO = ResourceKey.createRegistryKey(MuffinsMcAPI.modid("dripstone/fluid_chance_info"));

}
