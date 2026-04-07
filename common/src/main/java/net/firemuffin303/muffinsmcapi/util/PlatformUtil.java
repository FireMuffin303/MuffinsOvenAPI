package net.firemuffin303.muffinsmcapi.util;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class PlatformUtil {

    @ExpectPlatform
    public static boolean isFabric(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isNeoforge(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isJEIInstalled(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isEMIInstalled(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isDevelopment(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean emiEffectLocationHidden(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean emiEffectRenderTop(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean emiEffectIsCompressed(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean emiAgnosInventoryTabs(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean emiAgnosIsForge(){
        throw new AssertionError();
    }
}
