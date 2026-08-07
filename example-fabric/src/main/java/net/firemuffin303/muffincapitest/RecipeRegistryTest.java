package net.firemuffin303.muffincapitest;

import net.firemuffin303.muffinapitest.MuffinsAPITest;
import net.firemuffin303.muffinsmcapi.fabric.api.IRecipeBookInitializer;
import net.firemuffin303.muffinsmcapi.impl.recipebooks.OvenRecipeBookRegistry;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;

public class RecipeRegistryTest implements IRecipeBookInitializer {
    public static final String ECHO_CHAMBER_TYPE = "MUFFINS_API_TEST_ECHO_CHAMBER";
    public static final String ECHO_CHAMBER_SEARCH = "MUFFINS_API_TEST_ECHO_CHAMBER_SEARCH";
    public static final String ECHO_CHAMBER_BASE = "MUFFINS_API_TEST_ECHO_CHAMBER_BASE";

    @Override
    public void register(OvenRecipeBookRegistry recipeBookRegistry) {
        recipeBookRegistry.registerRecipeCategories(ECHO_CHAMBER_SEARCH,() -> new ItemStack[]{new ItemStack(Items.COMPASS)});
        recipeBookRegistry.registerRecipeCategories(ECHO_CHAMBER_BASE,() -> new ItemStack[]{new ItemStack(Items.ECHO_SHARD)});
        recipeBookRegistry.registerRecipeBook(ECHO_CHAMBER_TYPE, List.of(ECHO_CHAMBER_SEARCH,ECHO_CHAMBER_BASE));

        recipeBookRegistry.registerAggregateCategory(ECHO_CHAMBER_SEARCH, List.of(ECHO_CHAMBER_BASE));
    }
}
