package net.firemuffin303.muffinsmcapi.mixin.item;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.firemuffin303.muffinsmcapi.common.MuffinsDataComponents;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {
    @Shadow public abstract ItemStack getItem();

    @WrapWithCondition(method = "tick",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/item/ItemEntity;setUnderwaterMovement()V"))
    public boolean muffins$underWaterItemFloat(ItemEntity instance){
        return !instance.getItem().has(MuffinsDataComponents.HOVER);
    }

    @WrapWithCondition(method = "tick",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/item/ItemEntity;setUnderLavaMovement()V"))
    public boolean muffins$underLavaItemFloat(ItemEntity instance){
        return !instance.getItem().has(MuffinsDataComponents.HOVER);
    }

    @WrapWithCondition(method = "tick",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/item/ItemEntity;applyGravity()V"))
    public boolean muffins$gravityItemFloat(ItemEntity instance){
        return !instance.getItem().has(MuffinsDataComponents.HOVER);
    }

    @ModifyConstant(method = "tick", constant = @Constant(floatValue = 0.98f,ordinal = 0))
    public float muffins$applyHover(float constant){
        if(this.getItem().has(MuffinsDataComponents.HOVER)){
            return this.getItem().get(MuffinsDataComponents.HOVER).applyFriction(constant);
        }
        return constant;
    }
}
