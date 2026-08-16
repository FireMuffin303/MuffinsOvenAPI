package net.firemuffin303.muffinapitest.common.registry;

import net.firemuffin303.muffinapitest.MuffinsAPITest;
import net.firemuffin303.muffinapitest.common.block.JaronaBlock;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class ModBlocks {
    public static final ResourceRegistry<Block> BLOCK = ResourceRegistry.create(Registries.BLOCK, MuffinsAPITest.MOD_ID);
    public static final Supplier<Block> JARONA_BLOCK = BLOCK.register("jarona_block",() -> new JaronaBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE),ModItems.MINI_JARONA));
    public static final Supplier<Block> JAORANGE_BLOCK = BLOCK.register("jaorange_block",() -> new JaronaBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE),ModItems.MINI_JARONA));
}
