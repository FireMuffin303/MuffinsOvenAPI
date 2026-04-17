package net.firemuffin303.muffinsmcapi.util.forge;

import dev.emi.emi.config.EffectLocation;
import dev.emi.emi.config.EmiConfig;
import dev.emi.emi.platform.EmiAgnos;
import net.firemuffin303.muffinsmcapi.forge.common.ModEntityTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class PlatformUtilImpl {
    public static boolean isFabric() {
        return false;
    }

    public static boolean isNeoforge() {
        return true;
    }

    public static boolean isJEIInstalled() {
        return ModList.get().isLoaded("jei");
    }

    public static boolean isDevelopment() {
        return false;
    }

    public static boolean emiEffectLocationHidden() {
        return EmiConfig.effectLocation == EffectLocation.HIDDEN;
    }

    public static boolean emiEffectRenderTop() {
        return EmiConfig.effectLocation == EffectLocation.TOP;
    }

    public static boolean emiEffectIsCompressed() {
        return EmiConfig.effectLocation == EffectLocation.LEFT_COMPRESSED || EmiConfig.effectLocation == EffectLocation.RIGHT_COMPRESSED;
    }

    public static boolean emiAgnosInventoryTabs() {
        return EmiAgnos.isModLoaded("inventorytabs");
    }

    public static boolean emiAgnosIsForge() {
        return EmiAgnos.isForge();
    }

    public static boolean isEMIInstalled() {
        return ModList.get().isLoaded("emi");
    }

    public static ResourceLocation getBlock(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    public static <T extends Entity> Supplier<EntityType<T>> registerEntityType(String id, EntityType.Builder <T> entityType) {
        return ModEntityTypes.ENTITY_TYPE.register(id,() -> entityType.sized(1.375F, 0.5625F).clientTrackingRange(10).build(id));
    }
}