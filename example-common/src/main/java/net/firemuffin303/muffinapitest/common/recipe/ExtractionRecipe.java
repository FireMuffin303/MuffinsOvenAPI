package net.firemuffin303.muffinapitest.common.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.firemuffin303.muffinapitest.common.registry.ModRecipes;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class ExtractionRecipe implements Recipe<SingleRecipeInput> {
    private final ExtractionRecipeType extractionRecipeType;
    private final Ingredient ingredient;
    private final ItemStack output;

    public ExtractionRecipe(ExtractionRecipeType extractionRecipeType,Ingredient ingredient,ItemStack itemStack){
        this.extractionRecipeType = extractionRecipeType;
        this.ingredient = ingredient;
        this.output = itemStack;
    }

    public ExtractionRecipeType getExtractionRecipeType() {
        return extractionRecipeType;
    }

    @Override
    public boolean matches(SingleRecipeInput recipeInput, Level level) {
        return this.ingredient.test(recipeInput.item());
    }

    @Override
    public ItemStack assemble(SingleRecipeInput recipeInput, HolderLookup.Provider provider) {
        return this.output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonNullList = NonNullList.create();
        nonNullList.add(this.ingredient);
        return nonNullList;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.EXTRACTION_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.EXTRACTION_RECIPE_TYPE.get();
    }

    public enum ExtractionRecipeType implements StringRepresentable {
        FLUID("fluid"),
        GAS("gas");

        private final String id;
        public static final Codec<ExtractionRecipeType> CODEC = StringRepresentable.fromEnum(ExtractionRecipeType::values);


        ExtractionRecipeType(String id){
            this.id = id;
        }

        @Override
        public String getSerializedName() {
            return this.id;
        }
    }

    public static class Serializer implements RecipeSerializer<ExtractionRecipe>{
        public static final MapCodec<ExtractionRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
          return instance.group(
                  ExtractionRecipeType.CODEC.fieldOf("category").forGetter(extractionRecipe -> extractionRecipe.extractionRecipeType),
                  Ingredient.CODEC.fieldOf("ingredient").forGetter(extractionRecipe -> extractionRecipe.ingredient),
                  ItemStack.STRICT_CODEC.fieldOf("result").forGetter(extractionRecipe -> extractionRecipe.output)
          ).apply(instance,ExtractionRecipe::new);
        });

        public static final StreamCodec<RegistryFriendlyByteBuf, ExtractionRecipe> STREAM_CODEC = StreamCodec.of(ExtractionRecipe.Serializer::toNetwork, ExtractionRecipe.Serializer::fromNetwork);


        @Override
        public MapCodec<ExtractionRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ExtractionRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static ExtractionRecipe fromNetwork(RegistryFriendlyByteBuf registryFriendlyByteBuf) {
            ExtractionRecipeType extractionRecipeType1 = registryFriendlyByteBuf.readEnum(ExtractionRecipeType.class);
            Ingredient ingredient1 = Ingredient.CONTENTS_STREAM_CODEC.decode(registryFriendlyByteBuf);
            ItemStack result = ItemStack.STREAM_CODEC.decode(registryFriendlyByteBuf);
            return new ExtractionRecipe(extractionRecipeType1,ingredient1,result);
        }

        private static void toNetwork(RegistryFriendlyByteBuf registryFriendlyByteBuf, ExtractionRecipe extractionRecipe) {
            registryFriendlyByteBuf.writeEnum(extractionRecipe.extractionRecipeType);
            Ingredient.CONTENTS_STREAM_CODEC.encode(registryFriendlyByteBuf,extractionRecipe.ingredient);
            ItemStack.STREAM_CODEC.encode(registryFriendlyByteBuf, extractionRecipe.output);
        }
    }
}
