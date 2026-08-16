package net.firemuffin303.muffincapitest.mixin;

import net.firemuffin303.muffinapitest.common.registry.ModItems;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(RecipeBookCategories.class)
public enum RecipeCategoryMixin {
    MUFFINS_API_TEST_EXTRACTION_SEARCH(new ItemStack[]{new ItemStack(Items.CLOCK)}),
    MUFFINS_API_TEST_EXTRACTION_FLUID(new ItemStack[]{new ItemStack(ModItems.JAORANGE_BLOCK.get())}),
    MUFFINS_API_TEST_EXTRACTION_GAS(new ItemStack[]{new ItemStack(Items.WIND_CHARGE)});

    RecipeCategoryMixin(final ItemStack... itemStacks){

    }
}
