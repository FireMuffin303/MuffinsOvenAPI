package net.firemuffin303.muffinsmcapi.api;

import net.firemuffin303.muffinsmcapi.mixin.blockEntityType.BlockEntityTypeAccesor;
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
    public static void implementBlockEntityType(){
        for(Map.Entry<BlockEntityType<?>,List<Block>> entry : BLOCK_TYPE_MAP.entrySet()){
            BlockEntityTypeAccesor blockEntityTypeAccesor = ((BlockEntityTypeAccesor)entry.getKey());
            Set<Block> blockSet = new HashSet<>();
            blockSet.addAll(blockEntityTypeAccesor.getValidBlocks());
            blockSet.addAll(entry.getValue());
            blockEntityTypeAccesor.setValidBlocks(blockSet);
        }
    }
}
