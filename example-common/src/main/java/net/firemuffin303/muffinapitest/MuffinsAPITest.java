package net.firemuffin303.muffinapitest;

import net.firemuffin303.muffinapitest.common.registry.ModBoats;
import net.firemuffin303.muffinapitest.common.registry.ModItems;

public class MuffinsAPITest {
    public static final String MOD_ID = "muffins_api_test";

    public static void init(){
        ModBoats.init();
        ModItems.init();
    }
}
