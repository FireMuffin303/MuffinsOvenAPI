package net.firemuffin303.muffinapitest.forge;

import net.firemuffin303.muffinapitest.MuffinsAPITest;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(MuffinsAPITest.MOD_ID)
public class MuffinsAPITestForge {

    public MuffinsAPITestForge(IEventBus eventBus){
        MuffinsAPITest.init();
    }
}
