package net.firemuffin303.muffinsmcapi.forge.mixin.dripstone;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.muffinsmcapi.api.block.dripstone.DripstoneFluidRegistry;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.material.Fluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PointedDripstoneBlock.class)
public abstract class PointedDripstoneMixin {

    @ModifyExpressionValue(method = "maybeTransferFluid",at = @At(value = "CONSTANT", args = "floatValue=0.05859375",ordinal = 0))
    private static float muffins$getFluidChance(float original,@Local Fluid fluid){
        return DripstoneFluidRegistry.getFluidChance(fluid,original);
    }

    
}
