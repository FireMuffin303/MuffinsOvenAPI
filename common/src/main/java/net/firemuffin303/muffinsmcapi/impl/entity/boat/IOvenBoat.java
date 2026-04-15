package net.firemuffin303.muffinsmcapi.impl.entity.boat;

import net.minecraft.world.entity.vehicle.Boat;

import java.util.Optional;

public interface IOvenBoat {

    void setVariant(OvenBoatVariant variant);

    Optional<OvenBoatVariant> getOvenBoatVariant();

}
