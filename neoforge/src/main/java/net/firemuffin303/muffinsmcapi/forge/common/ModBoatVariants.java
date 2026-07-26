package net.firemuffin303.muffinsmcapi.forge.common;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.api.BoatRegistry;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Supplier;

public class ModBoatVariants {
    @ApiStatus.Internal
    public static final DeferredRegister<OvenBoatVariant> OVEN_BOAT_VARIANT = DeferredRegister.create(BoatRegistry.OVEN_BOAT_VARIANT, MuffinsMcAPI.MOD_ID);

}
