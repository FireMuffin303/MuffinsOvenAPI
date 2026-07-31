package net.firemuffin303.muffinapitest;

import net.firemuffin303.muffinapitest.common.registry.ModBoats;
import net.firemuffin303.muffinapitest.common.registry.ModItems;
import net.firemuffin303.muffinapitest.common.registry.ModMobEffects;
import net.firemuffin303.muffinsmcapi.api.block.dripstone.DripstoneFluidRegistry;
import net.firemuffin303.muffinsmcapi.impl.dripstone.DripstoneFluidEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;

public class MuffinsAPITest {
    public static final String MOD_ID = "muffins_api_test";

    public static void init(){
        ModBoats.init();
        ModItems.init();
        ModMobEffects.init();

        DripstoneFluidRegistry.SOURCE_STATE_EVENT.add(new DripstoneFluidEvent() {
            @Override
            public void onSourceStateChange(ServerLevel level, BlockPos tipPos, BlockPos sourcePos, BlockState sourceState, Fluid fluid) {
                if(sourceState.is(Blocks.CRYING_OBSIDIAN)){
                    BlockPos blockPos = findBlockStateBelowStalactiteTip(level,tipPos,predicate -> predicate.is(Blocks.OBSIDIAN));
                    if(blockPos != null){
                        BlockState beforeState = level.getBlockState(blockPos);
                        BlockState finalState = Blocks.CRYING_OBSIDIAN.defaultBlockState();
                        level.setBlockAndUpdate(blockPos,finalState);
                        Block.pushEntitiesUp(beforeState,finalState,level,blockPos);
                        level.gameEvent(GameEvent.BLOCK_CHANGE,sourcePos, GameEvent.Context.of(finalState));
                        level.levelEvent(1504,blockPos,0);
                    }
                }
            }
        });
    }
}
