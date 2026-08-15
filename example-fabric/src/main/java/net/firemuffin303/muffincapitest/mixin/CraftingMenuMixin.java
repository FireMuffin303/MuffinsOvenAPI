package net.firemuffin303.muffincapitest.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.firemuffin303.muffincapitest.ModRecipeBook;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.RecipeBookType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CraftingMenu.class)
public abstract class CraftingMenuMixin {

    @ModifyReturnValue(method = "getRecipeBookType",at = @At("RETURN"))
    public RecipeBookType muffins$debug(RecipeBookType original){
        return ModRecipeBook.ECHO_CHAMBER_TYPE;
    }
}
