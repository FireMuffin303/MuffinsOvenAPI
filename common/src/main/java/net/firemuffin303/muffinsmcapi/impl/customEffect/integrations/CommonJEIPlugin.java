package net.firemuffin303.muffinsmcapi.impl.customEffect.integrations;

import com.mojang.logging.LogUtils;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.runtime.IIngredientListOverlay;
import mezz.jei.api.runtime.IJeiRuntime;
import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@JeiPlugin
public class CommonJEIPlugin implements IModPlugin {
    private static @Nullable IJeiRuntime runtime;

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        runtime = jeiRuntime;
    }

    public static Optional<IJeiRuntime> getRuntime() {
        return Optional.ofNullable(runtime);
    }


    @Override
    public ResourceLocation getPluginUid() {
        return MuffinsMcAPI.modid("jei_plugin");
    }

    public static boolean checkOverlay(boolean bl){
        boolean ingredientListDisplayed = CommonJEIPlugin.getRuntime().map(IJeiRuntime::getIngredientListOverlay).map(IIngredientListOverlay::isListDisplayed).orElse(false);
        return ingredientListDisplayed ? false : bl;
    }
}
