package net.firemuffin303.muffinsmcapi.fabric;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.fabricmc.api.ModInitializer;
import net.firemuffin303.muffinsmcapi.api.BlockEntityTypeUtil;
import net.firemuffin303.muffinsmcapi.api.block.sign.OvenCeilingHangingSignBlock;
import net.firemuffin303.muffinsmcapi.api.block.sign.OvenStandSignBlock;
import net.firemuffin303.muffinsmcapi.api.block.sign.OvenWallHangingSignBlock;
import net.firemuffin303.muffinsmcapi.api.block.sign.OvenWallSignBlock;
import net.firemuffin303.muffinsmcapi.api.item.OvenBoatItem;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.BoatRegistry;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class MuffinsmcapiFabric implements ModInitializer {
    public static final Block OVEN_STANDING_SIGN = Registry.register(BuiltInRegistries.BLOCK,MuffinsMcAPI.modid("oven_standing_sign"),new OvenStandSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN),"oven"));
    public static final Block OVEN_WALL_SIGN = Registry.register(BuiltInRegistries.BLOCK,MuffinsMcAPI.modid("oven_wall_sign"),new OvenWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN),"oven"));
    public static final Item OVEN_SIGN_TEST = Registry.register(BuiltInRegistries.ITEM,MuffinsMcAPI.modid("oven_sign"),new SignItem(new Item.Properties().stacksTo(16),OVEN_STANDING_SIGN,OVEN_WALL_SIGN));


    public static final Block OVEN_CEILING_HANGING_SIGN = Registry.register(BuiltInRegistries.BLOCK,MuffinsMcAPI.modid("oven_hanging_sign"),new OvenCeilingHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN),"oven"));
    public static final Block OVEN_WALL_HANGING_SIGN = Registry.register(BuiltInRegistries.BLOCK,MuffinsMcAPI.modid("oven_wall_hanging_sign"),new OvenWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN),"oven"));
    public static final Item OVEN_HANGING_SIGN_TEST = Registry.register(BuiltInRegistries.ITEM,MuffinsMcAPI.modid("oven_hanging_sign"),new HangingSignItem(OVEN_CEILING_HANGING_SIGN,OVEN_WALL_HANGING_SIGN,new Item.Properties().stacksTo(16)));

    public static final OvenBoatVariant OVEN_BOAT = Registry.register(BoatRegistry.OVEN_BOAT_VARIANT_REGISTRY,MuffinsMcAPI.modid("oven"),new OvenBoatVariant(false));
    public static final OvenBoatVariant RAFT_OVEN_BOAT = Registry.register(BoatRegistry.OVEN_BOAT_VARIANT_REGISTRY,MuffinsMcAPI.modid("raft_oven"),new OvenBoatVariant(true));

    public static final Item OVEN_BOAT_ITEM = Registry.register(BuiltInRegistries.ITEM,MuffinsMcAPI.modid("oven_boat"),new OvenBoatItem(false,OVEN_BOAT));
    public static final Item CHEST_OVEN_BOAT_ITEM = Registry.register(BuiltInRegistries.ITEM,MuffinsMcAPI.modid("chest_oven_boat"),new OvenBoatItem(true,OVEN_BOAT));
    public static final Item OVEN_RAFT_ITEM = Registry.register(BuiltInRegistries.ITEM,MuffinsMcAPI.modid("oven_raft"),new OvenBoatItem(false,RAFT_OVEN_BOAT));
    public static final Item CHEST_OVEN_RAFT_ITEM = Registry.register(BuiltInRegistries.ITEM,MuffinsMcAPI.modid("chest_oven_raft"),new OvenBoatItem(true,RAFT_OVEN_BOAT));

    @Override
    public void onInitialize() {
        MuffinsMcAPI.init();
        BlockEntityTypeUtil.implementBlockEntityType();
    }


}
