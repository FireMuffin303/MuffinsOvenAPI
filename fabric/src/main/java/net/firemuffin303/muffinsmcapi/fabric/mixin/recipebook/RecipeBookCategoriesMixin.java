package net.firemuffin303.muffinsmcapi.fabric.mixin.recipebook;

import net.firemuffin303.muffinsmcapi.impl.recipebooks.OvenRecipeBookRegistry;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.inventory.RecipeBookType;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Debug(export = true)
@Mixin(RecipeBookCategories.class)
public abstract class RecipeBookCategoriesMixin {

    @Inject(method = "getCategories",at = @At("HEAD"), cancellable = true)
    private static void muffins$getRecipeCategories(RecipeBookType recipeBookType, CallbackInfoReturnable<List<RecipeBookCategories>> cir){
        if(OvenRecipeBookRegistry.INSTANCE.hasRecipeBookType(recipeBookType.name())){
            cir.setReturnValue(OvenRecipeBookRegistry.INSTANCE.getRecipeBookCategory(recipeBookType.name()));
        }
    }
}
