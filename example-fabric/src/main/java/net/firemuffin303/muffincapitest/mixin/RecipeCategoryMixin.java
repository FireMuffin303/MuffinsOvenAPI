package net.firemuffin303.muffincapitest.mixin;

import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(RecipeBookCategories.class)
public enum RecipeCategoryMixin {
    MUFFINS_API_TEST_ECHO_CHAMBER_SEARCH(new ItemStack[]{new ItemStack(Items.COMPASS)}),
    MUFFINS_API_TEST_ECHO_CHAMBER_BASE(new ItemStack[]{new ItemStack(Items.ECHO_SHARD)});

    RecipeCategoryMixin(final ItemStack... itemStacks){

    }
}
