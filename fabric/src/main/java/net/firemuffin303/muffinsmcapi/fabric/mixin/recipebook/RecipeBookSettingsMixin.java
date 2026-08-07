package net.firemuffin303.muffinsmcapi.fabric.mixin.recipebook;

import com.chocohead.mm.api.ClassTinkerers;
import com.mojang.datafixers.util.Pair;
import net.firemuffin303.muffinsmcapi.impl.recipebooks.OvenRecipeBookRegistry;
import net.minecraft.stats.RecipeBookSettings;
import net.minecraft.world.inventory.RecipeBookType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(RecipeBookSettings.class)
public class RecipeBookSettingsMixin {

    @Shadow @Final @Mutable
    private static Map<RecipeBookType, Pair<String, String>> TAG_FIELDS;

    @Shadow @Final private Map<RecipeBookType, RecipeBookSettings.TypeSettings> states;

    @Inject(method = "<init>()V",at = @At("TAIL"))
    public void muffins$init(CallbackInfo ci){
        Map<RecipeBookType,Pair<String,String>> newMap = new HashMap<>(TAG_FIELDS);
        OvenRecipeBookRegistry.INSTANCE.getRecipeBook().keySet().forEach(s -> {
            newMap.put(ClassTinkerers.getEnum(RecipeBookType.class,s),Pair.of("is"+s+"Open","is"+s+"FilteringCraftable"));
        });

        TAG_FIELDS = Map.copyOf(newMap);
    }

    @Inject(method = "<init>(Ljava/util/Map;)V",at = @At("TAIL"))
    public void muffins$initMap(Map map, CallbackInfo ci){
        OvenRecipeBookRegistry.INSTANCE.getRecipeBook().keySet().forEach(s -> {
            if(!states.containsKey(s)){
                states.put(ClassTinkerers.getEnum(RecipeBookType.class,s),new RecipeBookSettings.TypeSettings(false,false));
            }
        });
    }
}
