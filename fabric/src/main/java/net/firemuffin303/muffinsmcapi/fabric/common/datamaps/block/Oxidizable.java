package net.firemuffin303.muffinsmcapi.fabric.common.datamaps.block;

import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public record Oxidizable() {
    public static Map<Block,Block> OXIDIZABLE = new HashMap<>();
    public static Map<Block,Block> WAXABLE = new HashMap<>();


    public void init(){
        OXIDIZABLE.forEach(OxidizableBlocksRegistry::registerOxidizableBlockPair);
        WAXABLE.forEach(OxidizableBlocksRegistry::registerWaxableBlockPair);
    }
}
