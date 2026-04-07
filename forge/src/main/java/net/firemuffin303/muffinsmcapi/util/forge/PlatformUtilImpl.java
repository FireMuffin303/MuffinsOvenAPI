package net.firemuffin303.muffinsmcapi.util.forge;

import dev.emi.emi.config.EffectLocation;
import dev.emi.emi.config.EmiConfig;
import dev.emi.emi.platform.EmiAgnos;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoader;
import net.minecraftforge.fml.loading.FMLLoader;

public class PlatformUtilImpl {
    public static boolean isFabric() {
        return false;
    }

    public static boolean isNeoforge() {
        return true;
    }

    public static boolean isJEIInstalled() {
        return ModList.get().isLoaded("jei");
    }

    public static boolean isDevelopment() {
        return false;
    }

    public static boolean emiEffectLocationHidden() {
        return EmiConfig.effectLocation == EffectLocation.HIDDEN;
    }

    public static boolean emiEffectRenderTop() {
        return EmiConfig.effectLocation == EffectLocation.TOP;
    }

    public static boolean emiEffectIsCompressed() {
        return EmiConfig.effectLocation == EffectLocation.LEFT_COMPRESSED || EmiConfig.effectLocation == EffectLocation.RIGHT_COMPRESSED;
    }

    public static boolean emiAgnosInventoryTabs() {
        return EmiAgnos.isModLoaded("inventorytabs");
    }

    public static boolean emiAgnosIsForge() {
        return EmiAgnos.isForge();
    }

    public static boolean isEMIInstalled() {
        return ModList.get().isLoaded("emi");
    }
}