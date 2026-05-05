package net.firemuffin303.muffinsmcapi.impl.entity.boat;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

import java.util.function.Supplier;

public class OvenBoatItemDispenseBehavior extends DefaultDispenseItemBehavior {
    private final DefaultDispenseItemBehavior defaultDispenseItemBehavior;
    private final Supplier<OvenBoatVariant> ovenBoatVariant;
    private final boolean isChestBoat;

    public OvenBoatItemDispenseBehavior(Supplier<OvenBoatVariant> type) {
        this(type, false);
    }

    public OvenBoatItemDispenseBehavior(Supplier<OvenBoatVariant> type, boolean bl) {
        this.defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();
        this.ovenBoatVariant = type;
        this.isChestBoat = bl;
    }

    public ItemStack execute(BlockSource blockSource, ItemStack itemStack) {
        Direction direction = (Direction)blockSource.getBlockState().getValue(DispenserBlock.FACING);
        Level level = blockSource.getLevel();
        double d = 0.5625 + (double) OvenBoatUtil.OVEN_BOAT.get().getWidth() / 2.0;
        double e = blockSource.x() + (double)direction.getStepX() * d;
        double f = blockSource.y() + (double)((float)direction.getStepY() * 1.125F);
        double g = blockSource.z() + (double)direction.getStepZ() * d;
        BlockPos blockPos = blockSource.getPos().relative(direction);
        double h;
        if (((Level)level).getFluidState(blockPos).is(FluidTags.WATER)) {
            h = 1.0;
        } else {
            if (!((Level)level).getBlockState(blockPos).isAir() || !((Level)level).getFluidState(blockPos.below()).is(FluidTags.WATER)) {
                return this.defaultDispenseItemBehavior.dispense(blockSource, itemStack);
            }

            h = 0.0;
        }

        Boat boat = this.isChestBoat ? new OvenChestBoatEntity(level, e, f + h, g) : new OvenBoatEntity(level, e, f + h, g);
        ((IOvenBoat)boat).setVariant(this.ovenBoatVariant.get());
        ((Boat)boat).setYRot(direction.toYRot());
        ((Level)level).addFreshEntity((Entity)boat);
        itemStack.shrink(1);
        return itemStack;
    }

    protected void playSound(BlockSource blockSource) {
        blockSource.getLevel().levelEvent(1000, blockSource.getPos(), 0);
    }
}
