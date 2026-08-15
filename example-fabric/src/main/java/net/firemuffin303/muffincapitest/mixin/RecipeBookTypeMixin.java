package net.firemuffin303.muffincapitest.mixin;

import net.minecraft.world.inventory.RecipeBookType;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(RecipeBookType.class)
public enum RecipeBookTypeMixin {
    MUFFINS_API_TEST_ECHO_CHAMBER;

    RecipeBookTypeMixin(){

    }
}
