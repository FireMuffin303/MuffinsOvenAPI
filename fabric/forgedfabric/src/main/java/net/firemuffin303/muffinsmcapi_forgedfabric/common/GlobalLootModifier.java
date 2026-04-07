package net.firemuffin303.muffinsmcapi_forgedfabric.common;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi_forgedfabric.loot_modifiers.AddTableLootModifier;
import net.firemuffin303.muffinsmcapi_forgedfabric.loot_modifiers.IGlobalLootModifier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public record GlobalLootModifier(boolean replace, List<ResourceLocation> entries) {
    public static final Codec<GlobalLootModifier> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.BOOL.fieldOf("replace").forGetter(GlobalLootModifier::replace),
            ResourceLocation.CODEC.listOf().fieldOf("entries").forGetter(GlobalLootModifier::entries)
    ).apply(instance,GlobalLootModifier::new));


    public static final ResourceKey<Registry<MapCodec<? extends IGlobalLootModifier>>> GLOBAL_LOOT_MODIFIER_SERIALIZERS = ResourceKey.createRegistryKey(MuffinsMcAPI.modid("global_loot_modifier_serializers"));
    public static final Registry<MapCodec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIERS = FabricRegistryBuilder.createSimple(GLOBAL_LOOT_MODIFIER_SERIALIZERS).buildAndRegister();


    public static void init(){
        register(ResourceLocation.fromNamespaceAndPath("neoforge","add_table"),AddTableLootModifier.CODEC);
    }

    public static void register(ResourceLocation id,MapCodec<? extends IGlobalLootModifier> codec){
        Registry.register(GLOBAL_LOOT_MODIFIERS,id,codec);
    }
}
