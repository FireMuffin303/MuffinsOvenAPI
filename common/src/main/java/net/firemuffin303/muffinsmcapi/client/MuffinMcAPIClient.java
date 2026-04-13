package net.firemuffin303.muffinsmcapi.client;

import net.firemuffin303.muffinsmcapi.api.CustomEffectRegistry;
import net.firemuffin303.muffinsmcapi.api.ModIntegrationRegistry;
import net.firemuffin303.muffinsmcapi.util.PlatformUtil;

public class MuffinMcAPIClient {
    public static void init(){
        ModIntegrationRegistry.register("jei", PlatformUtil.isJEIInstalled());
        ModIntegrationRegistry.register("emi", PlatformUtil.isEMIInstalled());

        CustomEffectRegistry.init();
    }
}
