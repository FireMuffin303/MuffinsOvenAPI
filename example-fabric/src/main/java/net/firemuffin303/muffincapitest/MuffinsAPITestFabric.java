package net.firemuffin303.muffincapitest;

import net.fabricmc.api.ModInitializer;
import net.firemuffin303.muffinapitest.MuffinsAPITest;

public class MuffinsAPITestFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        MuffinsAPITest.init();
    }
}
