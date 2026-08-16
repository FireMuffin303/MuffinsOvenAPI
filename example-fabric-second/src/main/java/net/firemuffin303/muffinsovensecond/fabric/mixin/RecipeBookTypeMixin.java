package net.firemuffin303.muffinsovensecond.fabric.mixin;

import net.minecraft.world.inventory.RecipeBookType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RecipeBookType.class)
public enum RecipeBookTypeMixin {
    MUFFINS_OVEN_API_SECONDTEST_CHISEL;

    @Shadow
    RecipeBookTypeMixin(){

    }
}
