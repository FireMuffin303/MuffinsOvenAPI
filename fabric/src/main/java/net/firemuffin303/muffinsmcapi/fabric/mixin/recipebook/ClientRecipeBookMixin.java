package net.firemuffin303.muffinsmcapi.fabric.mixin.recipebook;

import net.firemuffin303.muffinsmcapi.impl.recipebooks.OvenRecipeBookRegistry;
import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;

@Mixin(ClientRecipeBook.class)
public abstract class ClientRecipeBookMixin {
    @Inject(method = "getCategory",at = @At("HEAD"), cancellable = true)
    private static void muffins$getCategory(RecipeHolder<?> recipeHolder, CallbackInfoReturnable<RecipeBookCategories> cir){
        OvenRecipeBookRegistry.RecipeCategoryEvent recipeCategoryEvent = OvenRecipeBookRegistry.INSTANCE.getRECIPE_EVENT().get(recipeHolder.value().getType());
        if(recipeCategoryEvent != null){
            Supplier<RecipeBookCategories> recipeBookCategories = recipeCategoryEvent.getCategory(recipeHolder);
            if(recipeBookCategories != null){
                cir.setReturnValue(recipeBookCategories.get());
            }
        }
    }
}
