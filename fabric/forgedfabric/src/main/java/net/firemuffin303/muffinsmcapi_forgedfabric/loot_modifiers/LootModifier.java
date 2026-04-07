package net.firemuffin303.muffinsmcapi_forgedfabric.loot_modifiers;

import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.AllOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.List;
import java.util.function.Predicate;

public abstract class LootModifier implements IGlobalLootModifier{
    protected final LootItemCondition[] conditions;
    private final Predicate<LootContext> combinedConditions;


    protected LootModifier(LootItemCondition[] conditionsIn) {
        this.conditions = conditionsIn;
        this.combinedConditions = AllOfCondition.allOf(List.of(conditionsIn));
    }

}
