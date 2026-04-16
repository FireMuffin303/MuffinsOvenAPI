package net.firemuffin303.muffinsmcapi.forge.common;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.api.item.OvenBoatItem;
import net.firemuffin303.muffinsmcapi.forge.MuffinsMCAPIForge;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static net.firemuffin303.muffinsmcapi.forge.common.ModBlocks.*;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, MuffinsMcAPI.MOD_ID);

    public static final Supplier<Item> OVEN_SIGN = ITEMS.register("oven_sign",() -> new SignItem(new Item.Properties().stacksTo(16),OVEN_STANDING_SIGN.get(),OVEN_WALL_SIGN.get()));
    public static final Supplier<Item> OVEN_HANGING_SIGN = ITEMS.register("oven_hanging_sign",() -> new HangingSignItem(OVEN_CEILING_HANGING_SIGN.get(),OVEN_WALL_HANGING_SIGN.get(),new Item.Properties().stacksTo(16)));
    public static final Supplier<Item> OVEN_BOAT = ITEMS.register("oven_boat",() -> new OvenBoatItem(false, ModBoatVariants.OVEN_VARIANT));
    public static final Supplier<Item> OVEN_CHEST_BOAT = ITEMS.register("oven_chest_boat",() -> new OvenBoatItem(true, ModBoatVariants.OVEN_VARIANT));

}
