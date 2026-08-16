package net.firemuffin303.muffinapitest.common.registry;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.api.item.OvenBoatItem;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class ModItems {
    public static final ResourceRegistry<Item> ITEM = ResourceRegistry.create(Registries.ITEM, MuffinsMcAPI.MOD_ID);

    public static final Supplier<Item> JARONA_BOAT = ITEM.register("jarona_boat",() -> new OvenBoatItem(false,ModBoats.JARONA));
    public static final Supplier<Item> JARONA_CHEST_BOAT = ITEM.register("jarona_chest_boat",() -> new OvenBoatItem(true,ModBoats.JARONA));
    public static final Supplier<Item> JARONA_BLOCK = ITEM.register("jarona_block",() -> new BlockItem(ModBlocks.JARONA_BLOCK.get(), new Item.Properties()));
    public static final Supplier<Item> MINI_JARONA = ITEM.register("mini_jarona",() -> new Item(new Item.Properties()));
    public static final Supplier<Item> JAORANGE_BLOCK = ITEM.register("jaorange_block",() -> new BlockItem(ModBlocks.JAORANGE_BLOCK.get(), new Item.Properties()));

    public static void init(){
        ITEM.init();
    }
}
