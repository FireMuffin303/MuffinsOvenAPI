package net.firemuffin303.muffinsmcapi.forge.common;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.BoatRegistry;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBoatVariants {

    public static final DeferredRegister<OvenBoatVariant> OVEN_BOAT_VARIANT = DeferredRegister.create(BoatRegistry.OVEN_BOAT_VARIANT, MuffinsMcAPI.MOD_ID);
    public static final Supplier<IForgeRegistry<OvenBoatVariant>> OVEN_BOAT_VARIANTS_REGISTRY = OVEN_BOAT_VARIANT.makeRegistry(() -> new RegistryBuilder<OvenBoatVariant>()
            .setName(BoatRegistry.OVEN_BOAT_VARIANT.registry())
            .allowModification()
            .setMaxID(2048));


    public static final RegistryObject<OvenBoatVariant> OVEN_VARIANT = OVEN_BOAT_VARIANT.register("oven",() -> new OvenBoatVariant(false));

}
