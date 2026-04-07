package net.firemuffin303.muffinsmcapi.util.fabric;

import dev.emi.emi.config.EffectLocation;
import dev.emi.emi.config.EmiConfig;
import dev.emi.emi.platform.EmiAgnos;
import net.fabricmc.loader.api.FabricLoader;

public class PlatformUtilImpl {
    public static boolean isFabric(){
        return true;
    }

    public static boolean isNeoforge() {
        return false;
    }

    public static boolean isJEIInstalled() {
        return FabricLoader.getInstance().isModLoaded("jei");
    }

    public static boolean isEMIInstalled() {
        return FabricLoader.getInstance().isModLoaded("emi");
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

    public static boolean isDevelopment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }



}
