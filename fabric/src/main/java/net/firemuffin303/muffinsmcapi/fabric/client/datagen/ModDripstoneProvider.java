package net.firemuffin303.muffinsmcapi.fabric.client.datagen;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.api.dripstone.DripstoneProvider;
import net.firemuffin303.muffinsmcapi.impl.dripstone.data.BlockFluidInfo;
import net.firemuffin303.muffinsmcapi.impl.dripstone.data.FluidChanceInfo;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;

import java.util.concurrent.CompletableFuture;

public class ModDripstoneProvider extends DripstoneProvider {
    public ModDripstoneProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(packOutput, completableFuture);
    }

    @Override
    public void generateDripstoneData(HolderLookup.Provider provider, BlockFluidOutput blockFluidOutput, FluidChanceOutput fluidChanceOutput) {
        blockFluidOutput.add(MuffinsMcAPI.modid("crying_obsidian"),new BlockFluidInfo(Blocks.CRYING_OBSIDIAN.defaultBlockState(), Fluids.LAVA));
        //fluidChanceOutput.add(MuffinsMcAPI.modid("lava"),new FluidChanceInfo(Fluids.LAVA,0.055f));
    }
}
