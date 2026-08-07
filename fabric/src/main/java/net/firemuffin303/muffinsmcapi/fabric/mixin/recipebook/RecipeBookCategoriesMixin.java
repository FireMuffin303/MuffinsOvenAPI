package net.firemuffin303.muffinsmcapi.fabric.mixin.recipebook;

import net.firemuffin303.muffinsmcapi.impl.recipebooks.OvenRecipeBookRegistry;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.inventory.RecipeBookType;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Debug(export = true)
@Mixin(RecipeBookCategories.class)
public abstract class RecipeBookCategoriesMixin {

    @Shadow @Final @Mutable
    public static Map<RecipeBookCategories, List<RecipeBookCategories>> AGGREGATE_CATEGORIES;

    @Inject(method = "getCategories",at = @At("HEAD"), cancellable = true)
    private static void muffins$getRecipeCategories(RecipeBookType recipeBookType, CallbackInfoReturnable<List<RecipeBookCategories>> cir){
        if(OvenRecipeBookRegistry.INSTANCE.hasRecipeBookType(recipeBookType.name())){
            cir.setReturnValue(OvenRecipeBookRegistry.INSTANCE.getRecipeBookCategory(recipeBookType.name()));
        }
    }

    @Unique
    private static Map<RecipeBookCategories,List<RecipeBookCategories>> createAggregateCategory(){
        Map<RecipeBookCategories,List<RecipeBookCategories>> map = new HashMap<>(AGGREGATE_CATEGORIES);
        OvenRecipeBookRegistry.INSTANCE.getMODDED_AGGREGATE_CATEGORY().forEach((type,list) ->{
            map.put(RecipeBookCategories.valueOf(type),list.stream().map(RecipeBookCategories::valueOf).toList());
        });
        return map;
    }

    static {
        AGGREGATE_CATEGORIES = createAggregateCategory();
    }
}
