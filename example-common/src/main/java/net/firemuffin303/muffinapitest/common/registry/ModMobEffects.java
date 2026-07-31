package net.firemuffin303.muffinapitest.common.registry;

import net.firemuffin303.muffinapitest.MuffinsAPITest;
import net.firemuffin303.muffinapitest.common.ModMobEffect;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

public class ModMobEffects {
    public static final ResourceRegistry<MobEffect> MOB_EFFECT_TEST = ResourceRegistry.create(Registries.MOB_EFFECT, MuffinsAPITest.MOD_ID);
    public static final ResourceRegistry<Potion> POTION_TEST = ResourceRegistry.create(Registries.POTION, MuffinsAPITest.MOD_ID);

    //TODO : field holder not the same from registration holder.
    public static final Holder<MobEffect> JARONA = MOB_EFFECT_TEST.registerHolder("jarona",() -> new ModMobEffect(MobEffectCategory.BENEFICIAL, 3402751));
    public static final Holder<Potion> JARONA_POTION = POTION_TEST.registerHolder("jarona",() -> new Potion(new MobEffectInstance(JARONA,20*20)));


    public static void init(){
        MOB_EFFECT_TEST.init();
        POTION_TEST.init();
    }
}
