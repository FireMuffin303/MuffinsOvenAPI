package net.firemuffin303.muffincapitest;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.mixin.itemgroup.ItemGroupsMixin;
import net.firemuffin303.muffinapitest.MuffinsAPITest;
import net.firemuffin303.muffinapitest.common.recipe.ExtractionRecipe;
import net.firemuffin303.muffinapitest.common.registry.ModBlocks;
import net.firemuffin303.muffinapitest.common.registry.ModItems;
import net.firemuffin303.muffinapitest.common.registry.ModMobEffects;
import net.firemuffin303.muffinapitest.common.registry.ModRecipes;
import net.firemuffin303.muffinsmcapi.impl.recipebooks.OvenRecipeBookRegistry;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MuffinsAPITestFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        MuffinsAPITest.init();

        OvenRecipeBookRegistry.INSTANCE.registerRecipeBook(RecipeBookType.MUFFINS_API_TEST_EXTRACTION_TYPE,
                () -> List.of(
                        RecipeBookCategories.MUFFINS_API_TEST_EXTRACTION_SEARCH,
                        RecipeBookCategories.MUFFINS_API_TEST_EXTRACTION_FLUID,
                        RecipeBookCategories.MUFFINS_API_TEST_EXTRACTION_GAS
                ));

        OvenRecipeBookRegistry.INSTANCE.registerAggregateCategory(
                () -> RecipeBookCategories.MUFFINS_API_TEST_EXTRACTION_SEARCH,
                () -> List.of(
                        RecipeBookCategories.MUFFINS_API_TEST_EXTRACTION_FLUID,
                        RecipeBookCategories.MUFFINS_API_TEST_EXTRACTION_GAS
                ));

        OvenRecipeBookRegistry.INSTANCE.registerRecipeCategoryEvent(ModRecipes.EXTRACTION_RECIPE_TYPE.get(),new OvenRecipeBookRegistry.RecipeCategoryEvent() {
            @Override
            public Supplier<RecipeBookCategories> getCategory(RecipeHolder<?> recipeHolder) {
                if(recipeHolder.value() instanceof ExtractionRecipe recipe){
                    return switch (recipe.getExtractionRecipeType()){
                        case FLUID -> () -> RecipeBookCategories.MUFFINS_API_TEST_EXTRACTION_FLUID;
                        case GAS -> () -> RecipeBookCategories.MUFFINS_API_TEST_EXTRACTION_GAS;
                    };
                }

                return () -> RecipeBookCategories.MUFFINS_API_TEST_EXTRACTION_FLUID;
            }
        });

        UseItemCallback.EVENT.register(new UseItemCallback() {
            @Override
            public InteractionResultHolder<ItemStack> interact(Player player, Level level, InteractionHand interactionHand) {
                ItemStack itemStack = player.getItemInHand(interactionHand);
                if(itemStack.is(Items.STICK)){
                    if(level.isClientSide){
                        LogUtils.getLogger().info("{}",player.hasEffect(ModMobEffects.JARONA));

                    }
                    return InteractionResultHolder.success(itemStack);
                }
                return InteractionResultHolder.pass(itemStack);
            }
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(new ItemGroupEvents.ModifyEntries() {
            @Override
            public void modifyEntries(FabricItemGroupEntries fabricItemGroupEntries) {
                fabricItemGroupEntries.accept(ModItems.JARONA_BLOCK.get());
            }
        });
    }
}
