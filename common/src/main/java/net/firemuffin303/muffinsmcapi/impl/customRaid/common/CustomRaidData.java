package net.firemuffin303.muffinsmcapi.impl.customRaid.common;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;

import java.util.List;

public class CustomRaidData {
    public static final Codec<CustomRaidData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        BuiltInRegistries.MOB_EFFECT.byNameCodec().fieldOf("raid_effect").forGetter(customRaidData -> customRaidData.raidEffect),
            RaiderData.CODEC.listOf().fieldOf("raid_data").forGetter(customRaidData -> customRaidData.raiders)
    ).apply(instance,CustomRaidData::new));

    private final List<RaiderData> raiders;
    private final MobEffect raidEffect;

    public CustomRaidData(MobEffect mobEffect,List<RaiderData> raiderData){
        this.raidEffect = mobEffect;
        this.raiders = raiderData;
    }

    public record RaiderData(EntityType<?> entityType,List<Integer> spawnRates){
        public static final Codec<RaiderData> CODEC = RecordCodecBuilder.create(raiderDataInstance -> raiderDataInstance.group(
                BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("entity_type").forGetter(RaiderData::entityType),
                Codec.INT.listOf().fieldOf("spawnRates").forGetter(RaiderData::spawnRates)
        ).apply(raiderDataInstance,RaiderData::new));
    }

}
