package net.firemuffin303.muffinsmcapi.api;

import java.util.HashMap;
import java.util.Map;

public class ModIntegrationRegistry {
    private static final Map<String,Boolean> MOD_INTEGRATIONS = new HashMap<>();


    public static void register(String modid,boolean value){
        MOD_INTEGRATIONS.put(modid,value);
    }

    public static boolean isModInstalled(String modid){
        return MOD_INTEGRATIONS.getOrDefault(modid,false);
    }

    public static boolean isJEIInstalled(){
        return isModInstalled("jei");
    }

    public static boolean isEMIInstalled(){
        return isModInstalled("emi");
    }
}
