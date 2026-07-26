package net.firemuffin303.muffinsmcapi.fabric.mixin.dripstone;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.muffinsmcapi.api.block.dripstone.DripstoneFluidRegistry;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PointedDripstoneBlock.class)
public abstract class PointedDripstoneBlockMixin {

    @ModifyExpressionValue(method = "maybeTransferFluid",at = @At(value = "CONSTANT", args = "floatValue=0.05859375",ordinal = 1))
    private static float muffins$getFluidChance(float original,@Local Fluid fluid){
        return DripstoneFluidRegistry.getFluidChance(fluid,original);
    }

    @Definition(id = "fluid",local = @Local(type = Fluid.class))
    @Definition(id ="LAVA",field = "Lnet/minecraft/world/level/material/Fluids;LAVA:Lnet/minecraft/world/level/material/FlowingFluid;")
    @Expression("fluid != LAVA")
    @WrapOperation(method = "maybeTransferFluid", at = @At("MIXINEXTRAS:EXPRESSION"))
    private static boolean muffins$isFluidInAPI(Object left, Object right, Operation<Boolean> original){
        if(left != Fluids.LAVA && DripstoneFluidRegistry.FLUID_DRIPSTONE_CHANCE.containsKey((Fluid) left)){
            return false;
        }

        return original.call(left,right);
    }
}