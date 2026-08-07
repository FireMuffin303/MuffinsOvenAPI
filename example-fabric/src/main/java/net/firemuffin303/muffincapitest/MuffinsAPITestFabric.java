package net.firemuffin303.muffincapitest;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.firemuffin303.muffinapitest.MuffinsAPITest;
import net.firemuffin303.muffinapitest.common.registry.ModMobEffects;
import net.firemuffin303.muffinsmcapi.impl.recipebooks.OvenRecipeBookRegistry;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.HashSet;
import java.util.stream.Collectors;

public class MuffinsAPITestFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        MuffinsAPITest.init();

        OvenRecipeBookRegistry.INSTANCE.registerRecipeCategoryEvent(RecipeType.CRAFTING,new OvenRecipeBookRegistry.RecipeCategoryEvent() {
            @Override
            public RecipeBookCategories getCategory( RecipeHolder<?> recipeHolder) {
                return RecipeBookCategories.valueOf(RecipeRegistryTest.ECHO_CHAMBER_BASE);
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
    }
}
