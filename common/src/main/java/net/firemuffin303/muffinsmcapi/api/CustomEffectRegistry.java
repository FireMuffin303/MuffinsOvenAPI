package net.firemuffin303.muffinsmcapi.api;

import net.firemuffin303.muffinsmcapi.impl.customEffect.CustomEffectRenderer;

import java.util.ArrayList;
import java.util.List;

/**Registration API for {@link CustomEffectRenderer}**/
public class CustomEffectRegistry {
    private static final List<CustomEffectRenderer> EFFECTS = new ArrayList<>();


    public static void init(){}

    /**
     * Register a custom effect renderer to display along the effect toast on inventory screen.
     * @param customEffectRenderer the custom effect renderer.
     * **/
    public static void register(CustomEffectRenderer customEffectRenderer){
        EFFECTS.add(customEffectRenderer);
    }


    /**Get all {@link CustomEffectRenderer} that has been registered.**/
    public static List<CustomEffectRenderer> getEffects(){
        return EFFECTS;
    }
}
