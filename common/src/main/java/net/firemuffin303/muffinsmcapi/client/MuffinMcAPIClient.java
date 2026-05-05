package net.firemuffin303.muffinsmcapi.client;

import net.firemuffin303.muffinsmcapi.api.CustomEffectRegistry;
import net.firemuffin303.muffinsmcapi.api.ModIntegrationHelper;

public class MuffinMcAPIClient {
    public static void init(){
        ModIntegrationHelper.register("jei");
        ModIntegrationHelper.register("emi");

        CustomEffectRegistry.init();
    }
}
