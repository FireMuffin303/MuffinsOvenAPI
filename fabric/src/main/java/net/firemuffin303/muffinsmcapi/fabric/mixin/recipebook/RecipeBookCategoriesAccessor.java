package net.firemuffin303.muffinsmcapi.fabric.mixin.recipebook;

import net.minecraft.client.RecipeBookCategories;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import java.util.Map;

@Mixin(RecipeBookCategories.class)
public interface RecipeBookCategoriesAccessor {

    @Mutable
    @Accessor("AGGREGATE_CATEGORIES")
    static void setAggregateCategory(Map<RecipeBookCategories, List<RecipeBookCategories>> map){
        throw new AssertionError();
    }
}
