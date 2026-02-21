package net.firemuffin303.muffinsmcapi.impl.customEffect.integrations;

import net.firemuffin303.muffinsmcapi.impl.customEffect.CustomEffectRenderer;
import net.firemuffin303.muffinsmcapi.util.PlatformUtil;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;

import java.util.Collection;
import java.util.List;

public class EMIEffectIntegration {

    public static Collection<CustomEffectRenderer> shouldHidden(Collection<CustomEffectRenderer> collection) {
        if (PlatformUtil.emiEffectLocationHidden()) {
            return List.of();
        }

        return collection;
    }

    public static boolean shouldRenderTop() {
        return PlatformUtil.emiEffectRenderTop();
    }

    public static boolean checkCompressed(boolean bl) {
        if (PlatformUtil.emiEffectIsCompressed()) {
            return false;
        }

        return bl;
    }

    public static int yAdjusting(int value, Screen screen) {
        int a = value;
        if (screen instanceof CreativeModeInventoryScreen || PlatformUtil.emiAgnosInventoryTabs()) {
            a -= 28;

            if (screen instanceof CreativeModeInventoryScreen && PlatformUtil.emiAgnosIsForge()) {
                a -= 22;
            }
        }
        return a;
    }
}
