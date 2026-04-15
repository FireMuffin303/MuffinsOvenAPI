package net.firemuffin303.muffinsmcapi.forge.common;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.api.block.sign.OvenCeilingHangingSignBlock;
import net.firemuffin303.muffinsmcapi.api.block.sign.OvenStandSignBlock;
import net.firemuffin303.muffinsmcapi.api.block.sign.OvenWallHangingSignBlock;
import net.firemuffin303.muffinsmcapi.api.block.sign.OvenWallSignBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(Registries.BLOCK, MuffinsMcAPI.MOD_ID);

    public static final Supplier<Block> OVEN_STANDING_SIGN = BLOCK.register("oven_standing_sign",() -> new OvenStandSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN),"oven"));
    public static final Supplier<Block> OVEN_WALL_SIGN = BLOCK.register("oven_wall_sign",() -> new OvenWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN),"oven"));
    public static final Supplier<Block> OVEN_CEILING_HANGING_SIGN = BLOCK.register("oven_ceiling_hanging_sign",() -> new OvenCeilingHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN),"oven"));
    public static final Supplier<Block> OVEN_WALL_HANGING_SIGN = BLOCK.register("oven_wall_hanging_sign",() -> new OvenWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN),"oven"));

}
