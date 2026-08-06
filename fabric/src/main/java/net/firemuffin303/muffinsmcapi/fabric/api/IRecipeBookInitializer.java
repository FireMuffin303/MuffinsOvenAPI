package net.firemuffin303.muffinsmcapi.fabric.api;

import net.firemuffin303.muffinsmcapi.impl.recipebooks.OvenRecipeBookRegistry;

public interface IRecipeBookInitializer {
    void register(OvenRecipeBookRegistry recipeBookRegistry);
}
