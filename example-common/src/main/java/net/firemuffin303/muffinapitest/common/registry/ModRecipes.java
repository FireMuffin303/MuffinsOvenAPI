package net.firemuffin303.muffinapitest.common.registry;

import net.firemuffin303.muffinapitest.MuffinsAPITest;
import net.firemuffin303.muffinapitest.common.recipe.ExtractionRecipe;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public class ModRecipes {
    public static final ResourceRegistry<RecipeType<?>> RECIPE = ResourceRegistry.create(Registries.RECIPE_TYPE, MuffinsAPITest.MOD_ID);
    public static final ResourceRegistry<RecipeSerializer<?>> RECIPE_SERIALIZER = ResourceRegistry.create(Registries.RECIPE_SERIALIZER, MuffinsAPITest.MOD_ID);


    public static final Supplier<RecipeType<ExtractionRecipe>> EXTRACTION_RECIPE_TYPE =
            (Supplier<RecipeType<ExtractionRecipe>>) (Object) RECIPE.register("extraction",() -> new RecipeType<>(){
        @Override
        public String toString() {
            return "extraction";
        }
    });

    public static final Supplier<RecipeSerializer<ExtractionRecipe>> EXTRACTION_RECIPE_SERIALIZER =
            (Supplier<RecipeSerializer<ExtractionRecipe>>) (Object) RECIPE_SERIALIZER.register("extraction", ExtractionRecipe.Serializer::new);


}
