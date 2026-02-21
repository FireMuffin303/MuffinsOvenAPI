package net.firemuffin303.muffinsmcapi.impl.dripstone;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public interface DripstoneFluidEvent {

    void onSourceStateChange(ServerLevel level,BlockPos tipPos, BlockPos sourcePos, BlockState sourceState, Fluid fluid);

    default BlockPos findBlockStateBelowStalactiteTip(Level level, BlockPos blockPos, Predicate<BlockState> predicate){
        BiPredicate<BlockPos, BlockState> biPredicate = (blockPosx, blockState) -> {
            return canDripThrough(level, blockPosx, blockState);
        };


        return findBlockVertical(level,blockPos,Direction.DOWN.getAxisDirection(),biPredicate,predicate,11).orElse(null);
    }

    private static boolean canDripThrough(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        if (blockState.isAir()) {
            return true;
        } else if (blockState.isSolidRender(blockGetter, blockPos)) {
            return false;
        } else if (!blockState.getFluidState().isEmpty()) {
            return false;
        } else {
            VoxelShape voxelShape = blockState.getCollisionShape(blockGetter, blockPos);
            return !Shapes.joinIsNotEmpty(Block.box(6.0, 0.0, 6.0, 10.0, 16.0, 10.0), voxelShape, BooleanOp.AND);
        }
    }

    private static Optional<BlockPos> findBlockVertical(LevelAccessor levelAccessor, BlockPos blockPos, Direction.AxisDirection axisDirection, BiPredicate<BlockPos, BlockState> biPredicate, Predicate<BlockState> predicate, int i) {
        Direction direction = Direction.get(axisDirection, Direction.Axis.Y);
        BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();

        for(int j = 1; j < i; ++j) {
            mutableBlockPos.move(direction);
            BlockState blockState = levelAccessor.getBlockState(mutableBlockPos);
            if (predicate.test(blockState)) {
                return Optional.of(mutableBlockPos.immutable());
            }

            if (levelAccessor.isOutsideBuildHeight(mutableBlockPos.getY()) || !biPredicate.test(mutableBlockPos, blockState)) {
                return Optional.empty();
            }
        }

        return Optional.empty();
    }
}
