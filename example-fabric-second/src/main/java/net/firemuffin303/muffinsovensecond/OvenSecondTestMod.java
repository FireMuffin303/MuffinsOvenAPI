package net.firemuffin303.muffinsovensecond;

import net.fabricmc.api.ModInitializer;
import net.firemuffin303.muffinsovensecond.fabric.common.ModItems;

public class OvenSecondTestMod implements ModInitializer {
    public static final String MODID = "muffins_oven_api_secondtest";

    @Override
    public void onInitialize() {
        ModItems.ITEM.init();
    }
}
