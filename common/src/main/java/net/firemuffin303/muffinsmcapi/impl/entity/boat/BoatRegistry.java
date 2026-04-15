package net.firemuffin303.muffinsmcapi.impl.entity.boat;

import net.firemuffin303.muffinsmcapi.util.PlatformUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.Boat;

import java.util.function.Supplier;

public class BoatRegistry {
    public static final Supplier<EntityType<OvenBoatEntity>> OVEN_BOAT = PlatformUtil.registerEntityType("oven_boat",EntityType.Builder.of(OvenBoatEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10));
}
