package net.firemuffin303.muffinsmcapi.forge.api.registry;

import net.firemuffin303.muffinsmcapi.api.BoatRegistry;
import net.firemuffin303.muffinsmcapi.forge.common.ModBoatVariants;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

public class ForgeOvenRegistry {
    public static final Supplier<IForgeRegistry<OvenBoatVariant>> OVEN_BOAT_VARIANTS_REGISTRY = ModBoatVariants.OVEN_BOAT_VARIANT.makeRegistry(() -> new RegistryBuilder<OvenBoatVariant>()
            .setName(BoatRegistry.OVEN_BOAT_VARIANT.registry())
            .allowModification()
            .setMaxID(2048));
}
