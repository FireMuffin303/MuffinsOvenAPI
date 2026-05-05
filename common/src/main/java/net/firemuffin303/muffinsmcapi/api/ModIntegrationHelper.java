package net.firemuffin303.muffinsmcapi.api;

import net.firemuffin303.muffinsmcapi.util.PlatformUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A common helper class that check mod installation.
 * **/
public class ModIntegrationHelper {
    private static final List<String> MOD_INTEGRATIONS = new ArrayList<>();


    /**
     * Register a mod to check if installed.
     * @param modid the mod's id
     * **/
    public static void register(String modid){
        MOD_INTEGRATIONS.add(modid);
    }

    /**
     * Check if mod installed.
     * @param modid the mod's id
     * **/
    public static boolean isModInstalled(String modid){
        return PlatformUtil.isModInstalled(modid);
    }

    /**
     * Check if Just Enough Item installed.
     * **/
    public static boolean isJEIInstalled(){
        return isModInstalled("jei");
    }

    /**
     * Check if EMI installed.
     * **/
    public static boolean isEMIInstalled(){
        return isModInstalled("emi");
    }
}
