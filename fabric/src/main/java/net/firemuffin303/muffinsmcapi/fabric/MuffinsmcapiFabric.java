package net.firemuffin303.muffinsmcapi.fabric;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.fabricmc.api.ModInitializer;
import net.firemuffin303.muffinsmcapi.api.BlockEntityTypeUtil;

public final class MuffinsmcapiFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        MuffinsMcAPI.init();
        BlockEntityTypeUtil.implementBlockEntityType();
    }


}
