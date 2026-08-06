package net.firemuffin303.muffinsmcapi.fabric.mixin.recipebook;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.muffinsmcapi.impl.recipebooks.OvenRecipeBookRegistry;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Debug(export = true)
@Mixin(RecipeBookComponent.class)
public abstract class RecipeBookComponentMixin {

    @Definition(id = "CRAFTING_SEARCH", field = "Lnet/minecraft/client/RecipeBookCategories;CRAFTING_SEARCH:Lnet/minecraft/client/RecipeBookCategories;")
    @Definition(id = "recipeBookCategories", local = @Local(type = RecipeBookCategories.class))
    @Expression("recipeBookCategories == CRAFTING_SEARCH")
    @ModifyExpressionValue(method = "updateTabs",at = @At("MIXINEXTRAS:EXPRESSION"))
    public boolean muffins$checkRecipeType(boolean original, @Local RecipeBookCategories categories){
        if(OvenRecipeBookRegistry.INSTANCE.shouldShowRecipeBookIcon(categories)){
            return true;
        }

        return original;
    }
}
