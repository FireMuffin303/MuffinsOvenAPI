package net.firemuffin303.muffinsmcapi.fabric.common.datamaps.furnace_fuel;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

public record FurnaceFuel(int burnTime) {
    public static Map<Item, FurnaceFuel> FURNACE_FUEL = new HashMap<>();

    public static void init(){
        FURNACE_FUEL.forEach((item, furnaceFuel) -> {
            FuelRegistry.INSTANCE.add(item,furnaceFuel.burnTime());
        });
    }
}
