package net.firemuffin303.muffinsmcapi.impl.recipebooks;

import com.chocohead.mm.api.ClassTinkerers;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import org.apache.logging.log4j.util.InternalApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class OvenRecipeBookRegistry {
    public static OvenRecipeBookRegistry INSTANCE = new OvenRecipeBookRegistry();

    private final Map<String,List<String>> RECIPE_BOOKS_MAP = new HashMap<>();
    private final Map<String,RecipeCategory> RECIPE_CATEGORIES = new HashMap<>();
    private final Map<String, List<String>> MODDED_AGGREGATE_CATEGORY = new HashMap<>();
    private final Map<RecipeType<?>, RecipeCategoryEvent> RECIPE_EVENT = new HashMap<>();

    public void registerRecipeBook(String stationID, List<String> categories){
        List<String> upperCat = new ArrayList<>();
        for(String s : categories){
            upperCat.add(s.toUpperCase());
        }

        RECIPE_BOOKS_MAP.put(stationID.toUpperCase(),upperCat);
    }

    public void registerRecipeCategories(String id, Supplier<ItemStack[]> items){
        registerRecipeCategories(id,items,true);
    }

    public void registerRecipeCategories(String id, Supplier<ItemStack[]> items,boolean shouldShowRecipeBook){
        RECIPE_CATEGORIES.put(id.toUpperCase(),new RecipeCategory(id.toUpperCase(),items,shouldShowRecipeBook));
    }

    public void registerAggregateCategory(String recipeBookCategories, List<String> list){
        MODDED_AGGREGATE_CATEGORY.put(recipeBookCategories,list);
    }

    public void registerRecipeCategoryEvent(RecipeType<?> recipeType, RecipeCategoryEvent event){
        RECIPE_EVENT.put(recipeType,event);
    }

    @InternalApi
    public List<RecipeBookCategories> getRecipeBookCategory(String name) {
        return RECIPE_BOOKS_MAP.get(name).stream().map(s -> ClassTinkerers.getEnum(RecipeBookCategories.class,s.toUpperCase())).toList();
    }

    public Map<String, List<String>> getRecipeBook() {
        return RECIPE_BOOKS_MAP;
    }

    public boolean hasRecipeBookType(String name) {
        return RECIPE_BOOKS_MAP.containsKey(name);
    }

    public boolean hasRecipeBookCategory(String name) {
        return RECIPE_CATEGORIES.containsKey(name);
    }

    public List<RecipeCategory> getRecipeCategory(){
        return RECIPE_CATEGORIES.values().stream().toList();
    }

    public Map<String, List<String>> getMODDED_AGGREGATE_CATEGORY() {
        return MODDED_AGGREGATE_CATEGORY;
    }

    @InternalApi
    public Map<RecipeType<?>, RecipeCategoryEvent> getRECIPE_EVENT() {
        return RECIPE_EVENT;
    }

    @InternalApi
    public boolean shouldShowRecipeBookIcon(RecipeBookCategories recipeBookCategories){
        List<String> result = MODDED_AGGREGATE_CATEGORY.keySet().stream().filter(id -> id.equals(recipeBookCategories.name())).toList();
        return !result.isEmpty();
    }

    public record RecipeCategory(String id, Supplier<ItemStack[]> stacks,boolean shouldShow){

    }

    public interface RecipeCategoryEvent{
        RecipeBookCategories getCategory(RecipeHolder<?> recipeHolder);
    }

}
