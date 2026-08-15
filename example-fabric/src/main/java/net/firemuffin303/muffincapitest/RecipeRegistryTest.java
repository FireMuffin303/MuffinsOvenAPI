package net.firemuffin303.muffincapitest;

import net.firemuffin303.muffinsmcapi.fabric.api.IRecipeBookInitializer;
import net.firemuffin303.muffinsmcapi.impl.recipebooks.OvenRecipeBookRegistry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

public class RecipeRegistryTest implements IRecipeBookInitializer {
    public static final String ECHO_CHAMBER_TYPE = "MUFFINS_API_TEST_ECHO_CHAMBER";
    public static final String ECHO_CHAMBER_SEARCH = "MUFFINS_API_TEST_ECHO_CHAMBER_SEARCH";
    public static final String ECHO_CHAMBER_BASE = "MUFFINS_API_TEST_ECHO_CHAMBER_BASE";

    @Override
    public void register(OvenRecipeBookRegistry recipeBookRegistry) {
    }
}
