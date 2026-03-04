package net.firemuffin303.muffinsmcapi.api.dripstone;

import net.firemuffin303.muffinsmcapi.impl.dripstone.DripstoneFluidEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DripstoneFluidRegistry {
    public static final Map<BlockState, Fluid> FLUID = new HashMap<>();
    public static final Map<Fluid,Float> FLUID_DRIPSTONE_CHANCE = new HashMap<>();

    public static final List<DripstoneFluidEvent> SOURCE_STATE_EVENT = new ArrayList<>();

    public static void addEvent(DripstoneFluidEvent dripstoneFluidEvent){
        SOURCE_STATE_EVENT.add(dripstoneFluidEvent);
    }

    public static void register(BlockState blockState,Fluid fluid){
        if(FLUID.containsKey(blockState)){
            return;
        }

        FLUID.putIfAbsent(blockState,fluid);
    }

    public static float getFluidChance(Fluid fluid,float original){
        return FLUID_DRIPSTONE_CHANCE.getOrDefault(fluid,original);
    }

    public static boolean hasFluidInfo(BlockState blockState){
        return FLUID.containsKey(blockState);
    }

    public static Fluid getFluid(BlockState blockState){
        return FLUID.get(blockState);
    }
}
