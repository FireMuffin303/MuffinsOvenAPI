package net.firemuffin303.muffinsmcapi.api;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.ApiStatus;

import java.util.*;

public class BlockEntityTypeUtil {
    private static final Map<BlockEntityType<?>,List<Block>> BLOCK_TYPE_MAP = new HashMap<>();

    public static void addBlockEntityType(BlockEntityType<?> blockEntityType, Block... blocks){
        if(BLOCK_TYPE_MAP.containsKey(blockEntityType)){
            BLOCK_TYPE_MAP.get(blockEntityType).addAll(Arrays.asList(blocks));
            return;
        }
        BLOCK_TYPE_MAP.put(blockEntityType,new ArrayList<>(Arrays.asList(blocks)));
    }

    @ApiStatus.Internal
    public static boolean isBlockValid(BlockEntityType<?> blockEntityType,Block block) {
        if(BLOCK_TYPE_MAP.containsKey(blockEntityType)){
            List<Block> list = BLOCK_TYPE_MAP.get(blockEntityType);
            return list.contains(block);
        }
        return false;
    }

}
