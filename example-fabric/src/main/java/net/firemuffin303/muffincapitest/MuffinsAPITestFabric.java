package net.firemuffin303.muffincapitest;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import net.firemuffin303.muffinapitest.MuffinsAPITest;
import net.firemuffin303.muffinsmcapi.impl.recipebooks.OvenRecipeBookRegistry;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;

public class MuffinsAPITestFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        MuffinsAPITest.init();

        OvenRecipeBookRegistry.INSTANCE.registerRecipeCategoryEvent(RecipeType.CRAFTING,new OvenRecipeBookRegistry.RecipeCategoryEvent() {
            @Override
            public RecipeBookCategories getCategory( RecipeHolder<?> recipeHolder) {
                return RecipeBookCategories.valueOf(RecipeRegistryTest.ECHO_CHAMBER_BASE);
            }
        });
    }
}
