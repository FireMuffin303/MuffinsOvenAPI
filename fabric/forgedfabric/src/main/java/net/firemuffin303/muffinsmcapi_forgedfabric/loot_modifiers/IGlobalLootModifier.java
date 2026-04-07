package net.firemuffin303.muffinsmcapi_forgedfabric.loot_modifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.firemuffin303.muffinsmcapi_forgedfabric.ForgedFabricAPI;
import net.firemuffin303.muffinsmcapi_forgedfabric.common.GlobalLootModifier;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.List;
import java.util.function.Function;

public interface IGlobalLootModifier {
    Codec<IGlobalLootModifier> CODEC = GlobalLootModifier.GLOBAL_LOOT_MODIFIERS.byNameCodec()
            .dispatch(IGlobalLootModifier::codec, Function.identity());

    Codec<LootItemCondition[]> LOOT_CONDITIONS_CODEC = LootItemCondition.DIRECT_CODEC.listOf().xmap(list -> list.toArray(LootItemCondition[]::new), List::of);


    MapCodec<? extends IGlobalLootModifier> codec();

}
