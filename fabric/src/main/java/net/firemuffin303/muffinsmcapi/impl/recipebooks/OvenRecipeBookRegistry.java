package net.firemuffin303.muffinsmcapi.impl.recipebooks;

import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import org.apache.logging.log4j.util.InternalApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class OvenRecipeBookRegistry {
    public static OvenRecipeBookRegistry INSTANCE = new OvenRecipeBookRegistry();

    private final Map<RecipeBookType,Supplier<List<RecipeBookCategories>>> RECIPE_BOOKS_MAP = new HashMap<>();
    private final Map<Supplier<RecipeBookCategories>, Supplier<List<RecipeBookCategories>>> MODDED_AGGREGATE_CATEGORY = new HashMap<>();
    private final Map<RecipeType<?>, RecipeCategoryEvent> RECIPE_EVENT = new HashMap<>();

    public void registerRecipeBook(RecipeBookType stationID, Supplier<List<RecipeBookCategories>>  categories){
        RECIPE_BOOKS_MAP.put(stationID,categories);
    }

    public void registerAggregateCategory(Supplier<RecipeBookCategories> recipeBookCategories, Supplier<List<RecipeBookCategories> > list){
        MODDED_AGGREGATE_CATEGORY.put(recipeBookCategories,list);
    }

    public void registerRecipeCategoryEvent(RecipeType<?> recipeType, RecipeCategoryEvent event){
        RECIPE_EVENT.put(recipeType,event);
    }

    @InternalApi
    public List<RecipeBookCategories> getRecipeBookCategory(RecipeBookType recipeBookType) {
        return RECIPE_BOOKS_MAP.get(recipeBookType).get();
    }

    public Map<RecipeBookType, Supplier<List<RecipeBookCategories>>> getRecipeBook() {
        return RECIPE_BOOKS_MAP;
    }

    public boolean hasRecipeBookType(RecipeBookType name) {
        return RECIPE_BOOKS_MAP.containsKey(name);
    }

    public Map<Supplier<RecipeBookCategories>, Supplier<List<RecipeBookCategories>>> getMODDED_AGGREGATE_CATEGORY() {
        return MODDED_AGGREGATE_CATEGORY;
    }

    @InternalApi
    public Map<RecipeType<?>, RecipeCategoryEvent> getRECIPE_EVENT() {
        return RECIPE_EVENT;
    }

    @InternalApi
    public boolean shouldShowRecipeBookIcon(RecipeBookCategories recipeBookCategories){
        List<RecipeBookCategories> result = MODDED_AGGREGATE_CATEGORY.keySet().stream().map(Supplier::get).filter(id -> id.equals(recipeBookCategories)).toList();
        return !result.isEmpty();
    }

    public record RecipeCategory(String id, Supplier<ItemStack[]> stacks,boolean shouldShow){

    }

    public interface RecipeCategoryEvent{
        Supplier<RecipeBookCategories> getCategory(RecipeHolder<?> recipeHolder);
    }

}
