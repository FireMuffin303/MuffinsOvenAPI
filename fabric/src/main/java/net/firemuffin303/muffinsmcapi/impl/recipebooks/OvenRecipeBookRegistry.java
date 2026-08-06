package net.firemuffin303.muffinsmcapi.impl.recipebooks;

import com.chocohead.mm.api.ClassTinkerers;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.level.ItemLike;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class OvenRecipeBookRegistry {
    public static OvenRecipeBookRegistry INSTANCE = new OvenRecipeBookRegistry();

    private Map<String,RecipeTypeRegistration> RECIPE_BOOKS_MAP = new HashMap<>();
    private Map<String,RecipeCategory> RECIPE_CATEGORIES = new HashMap<>();
    private Map<ResourceLocation, List<ResourceLocation>> MODDED_AGGREGATE_CATEGORY = new HashMap<>();

    public void registerRecipeBook(ResourceLocation stationID, List<ResourceLocation> categories,String isOpen,String filter){
        RECIPE_BOOKS_MAP.put(convertID(stationID),new RecipeTypeRegistration(categories, Pair.of(isOpen,filter)));
    }

    public void registerRecipeCategories(ResourceLocation id, Supplier<List<ItemLike>> items){
        registerRecipeCategories(id,items,true);
    }

    public void registerRecipeCategories(ResourceLocation id, Supplier<List<ItemLike>> items,boolean shouldShowRecipeBook){
        RECIPE_CATEGORIES.put(convertID(id),new RecipeCategory(convertID(id),items,shouldShowRecipeBook));
    }

    public void registerAggregateCategory(ResourceLocation recipeBookCategories, List<ResourceLocation> list){
        MODDED_AGGREGATE_CATEGORY.put(recipeBookCategories,list);
    }

    public List<RecipeBookCategories> getRecipeBookCategory(String name) {
        return RECIPE_BOOKS_MAP.get(name).recipeCategoryID().stream().map(OvenRecipeBookRegistry::convertID).map(s -> ClassTinkerers.getEnum(RecipeBookCategories.class,s)).toList();
    }

    public Map<String, RecipeTypeRegistration> getRecipeBook() {
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

    public Map<ResourceLocation, List<ResourceLocation>> getMODDED_AGGREGATE_CATEGORY() {
        return MODDED_AGGREGATE_CATEGORY;
    }

    public boolean shouldShowRecipeBookIcon(RecipeBookCategories recipeBookCategories){
        List<ResourceLocation> result = MODDED_AGGREGATE_CATEGORY.keySet().stream().filter(resourceLocation -> convertID(resourceLocation).equals(recipeBookCategories.name())).toList();
        return !result.isEmpty();
    }

    public RecipeBookCategories getCategory(ResourceLocation id){
        String stringID = (id.getNamespace()+"_"+id.getPath()).toUpperCase();
        return ClassTinkerers.getEnum(RecipeBookCategories.class,stringID);
    }

    public RecipeBookType getRecipeBookType(ResourceLocation id){
        return ClassTinkerers.getEnum(RecipeBookType.class,convertID(id));
    }

    public static String convertID(ResourceLocation id){
        return (id.getNamespace()+"_"+id.getPath()).toUpperCase();
    }

    public record RecipeTypeRegistration(List<ResourceLocation> recipeCategoryID, Pair<String,String> pair){

    }

    public record RecipeCategory(String id, Supplier<List<ItemLike>> stacks,boolean shouldShow){

    }

}
