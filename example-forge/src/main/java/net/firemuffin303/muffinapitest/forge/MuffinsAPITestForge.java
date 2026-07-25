package net.firemuffin303.muffinapitest.forge;

import net.firemuffin303.muffinapitest.MuffinsAPITest;
import net.minecraftforge.fml.common.Mod;

@Mod(MuffinsAPITest.MOD_ID)
public class MuffinsAPITestForge {

    public MuffinsAPITestForge(){
        MuffinsAPITest.init();
    }
}
