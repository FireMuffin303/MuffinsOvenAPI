package net.firemuffin303.muffinsovensecond.fabric.common;

import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.firemuffin303.muffinsovensecond.OvenSecondTestMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class ModItems {
    public static final ResourceRegistry<Item> ITEM = ResourceRegistry.create(Registries.ITEM, OvenSecondTestMod.MODID);
    public static final Supplier<Item> DUMMY_ITEM = ITEM.register("dummy_item",() -> new Item(new Item.Properties()));
}
