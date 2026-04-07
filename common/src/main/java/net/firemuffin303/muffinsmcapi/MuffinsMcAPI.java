package net.firemuffin303.muffinsmcapi;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

public final class MuffinsMcAPI {
    public static final String MOD_ID = "muffinsmcapi";


    public static void init() {

    }

    public static ResourceLocation modid(String id){
        return new ResourceLocation(MOD_ID,id);
    }
}
