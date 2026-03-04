package net.firemuffin303.muffinsmcapi.mixin.block.amethyst;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.BuddingAmethystBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BuddingAmethystBlock.class)
public abstract class BuddingAmethystBlockMixin {

    @Inject(method = "randomTick",at = @At("HEAD"),cancellable = true)
    public void muffins$AmethystCrystalGrowEvent(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource, CallbackInfo ci){
        //TODO: do some condition bullshit to check. If fail all of them then do vanilla.
    }
}
