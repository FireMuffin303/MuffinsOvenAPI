package net.firemuffin303.muffinsovensecond.fabric.mixin;

import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RecipeBookCategories.class)
public enum RecipeCategoriesMixin {
    MUFFINS_OVEN_API_SECONDTEST_CHISEL_SEARCH(),
    MUFFINS_OVEN_API_SECONDTEST_CHISEL_BASE();

    @Shadow
    RecipeCategoriesMixin(ItemStack... itemstack){

    }
}
