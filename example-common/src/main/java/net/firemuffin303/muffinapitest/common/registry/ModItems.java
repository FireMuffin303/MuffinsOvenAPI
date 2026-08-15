package net.firemuffin303.muffinapitest.common.registry;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.api.item.OvenBoatItem;
import net.firemuffin303.muffinsmcapi.common.ModDataComponentTypes;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class ModItems {
    public static final ResourceRegistry<Item> ITEM = ResourceRegistry.create(Registries.ITEM, MuffinsMcAPI.MOD_ID);

    public static final Supplier<Item> JARONA_BOAT = ITEM.register("jarona_boat",() -> new BoatItem(false, Boat.Type.OAK,new Item.Properties().stacksTo(1).component(ModDataComponentTypes.OVEN_BOAT_TYPE.get(),)));
    public static final Supplier<Item> JARONA_CHEST_BOAT = ITEM.register("jarona_chest_boat",() -> new OvenBoatItem(true,ModBoats.JARONA));

    public static void init(){
        ITEM.init();
    }
}
