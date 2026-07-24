package net.firemuffin303.muffinapitest.common.registry;

import net.firemuffin303.muffinapitest.MuffinsAPITest;
import net.firemuffin303.muffinsmcapi.api.BoatRegistry;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Supplier;

public class ModBoats {
    public static final ResourceRegistry<OvenBoatVariant> BOAT_VARIANT = ResourceRegistry.create(BoatRegistry.OVEN_BOAT_VARIANT, MuffinsAPITest.MOD_ID);
    public static final Supplier<OvenBoatVariant> JARONA = BOAT_VARIANT.register("jarona",() -> new OvenBoatVariant.Builder(ModItems.JARONA_BOAT,ModItems.JARONA_CHEST_BOAT, () -> Blocks.ACACIA_PLANKS).build());


    public static void init(){
        BOAT_VARIANT.init();
    }
}
