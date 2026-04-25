package net.firemuffin303.muffinsmcapi.mixin.blockEntityType;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.muffinsmcapi.api.BlockEntityTypeUtil;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockEntityType.class)
public abstract class BlockEntityTypeMixin {

    @ModifyReturnValue(method = "isValid",at = @At("RETURN"))
    public boolean muffins$isValid(boolean original, @Local(argsOnly = true)BlockState blockState){
        if(original){
            return true;
        }

        BlockEntityType<?> blockEntityType = (BlockEntityType<?>) (Object) this;
        return BlockEntityTypeUtil.isBlockValid(blockEntityType,blockState.getBlock());
    }
}
