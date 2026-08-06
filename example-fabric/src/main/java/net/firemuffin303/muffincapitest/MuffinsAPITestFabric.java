package net.firemuffin303.muffincapitest;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import net.firemuffin303.muffinapitest.MuffinsAPITest;
import net.firemuffin303.muffinsmcapi.impl.recipebooks.OvenRecipeBookRegistry;
import net.minecraft.world.inventory.RecipeBookType;

public class MuffinsAPITestFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        MuffinsAPITest.init();

        LogUtils.getLogger().info("{}", OvenRecipeBookRegistry.INSTANCE.getRecipeBookType(RecipeRegistryTest.ECHO_CHAMBER_TYPE));
    }
}
