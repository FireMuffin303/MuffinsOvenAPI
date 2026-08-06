package net.firemuffin303.muffinsmcapi.fabric;

import com.chocohead.mm.api.ClassTinkerers;
import com.mojang.datafixers.util.Pair;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.firemuffin303.muffinsmcapi.fabric.mixin.recipebook.RecipeBookSettingsAccessor;
import net.firemuffin303.muffinsmcapi.impl.recipebooks.OvenRecipeBookRegistry;
import net.minecraft.world.inventory.RecipeBookType;

import java.util.HashMap;
import java.util.Map;

public class MuffinsOvenAPIPreLaunch implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        Map<RecipeBookType, Pair<String, String>> map = new HashMap<>(RecipeBookSettingsAccessor.getTagField());

        OvenRecipeBookRegistry.INSTANCE.getRecipeBook().forEach((s, recipeTypeRegistration) -> {
            map.put(ClassTinkerers.getEnum(RecipeBookType.class,s),recipeTypeRegistration.pair());
        });

        RecipeBookSettingsAccessor.setTagField(map);
    }
}
