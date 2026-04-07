package net.firemuffin303.muffinsmcapi_forgedfabric.common;

import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi_forgedfabric.biome_modifiers.IBiomeModifier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class BiomeModifiers {
    public static final ResourceKey<Registry<MapCodec<? extends IBiomeModifier>>> BIOME_MODIFIERS_SERIALIZERS = ResourceKey.createRegistryKey(MuffinsMcAPI.modid("biome_modifier_serializers"));
    public static final Registry<MapCodec<? extends IBiomeModifier>> BIOME_MODIFIERS = FabricRegistryBuilder.createSimple(BIOME_MODIFIERS_SERIALIZERS).buildAndRegister();

    public static void init(){

    }

    public static void register(ResourceLocation id,MapCodec<? extends IBiomeModifier> codec){
        Registry.register(BIOME_MODIFIERS,id,codec);
    }
}
