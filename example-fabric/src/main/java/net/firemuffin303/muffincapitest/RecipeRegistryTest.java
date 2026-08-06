package net.firemuffin303.muffincapitest;

import net.firemuffin303.muffinapitest.MuffinsAPITest;
import net.firemuffin303.muffinsmcapi.fabric.api.IRecipeBookInitializer;
import net.firemuffin303.muffinsmcapi.impl.recipebooks.OvenRecipeBookRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.List;

public class RecipeRegistryTest implements IRecipeBookInitializer {
    public static final ResourceLocation ECHO_CHAMBER_TYPE = ResourceLocation.fromNamespaceAndPath(MuffinsAPITest.MOD_ID,"echo_chamber");
    public static final ResourceLocation ECHO_CHAMBER_SEARCH = ResourceLocation.fromNamespaceAndPath(MuffinsAPITest.MOD_ID,"echo_chamber_search");
    public static final ResourceLocation ECHO_CHAMBER_BASE = ResourceLocation.fromNamespaceAndPath(MuffinsAPITest.MOD_ID,"echo_chamber_base");

    @Override
    public void register(OvenRecipeBookRegistry recipeBookRegistry) {
        recipeBookRegistry.registerRecipeCategories(ECHO_CHAMBER_SEARCH,() -> List.of(Items.COMPASS));
        recipeBookRegistry.registerRecipeCategories(ECHO_CHAMBER_BASE,() -> List.of(Items.ECHO_SHARD));
        recipeBookRegistry.registerRecipeBook(ECHO_CHAMBER_TYPE,
                List.of(ECHO_CHAMBER_SEARCH,ECHO_CHAMBER_BASE),"isEchoChamberOpen","isEchoChamberFilteringCraftable");

        recipeBookRegistry.registerAggregateCategory(ECHO_CHAMBER_SEARCH, List.of(ECHO_CHAMBER_BASE));
    }
}
