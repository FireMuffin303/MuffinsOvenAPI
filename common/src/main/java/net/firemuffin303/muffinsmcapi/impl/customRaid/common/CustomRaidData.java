package net.firemuffin303.muffinsmcapi.impl.customRaid.common;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;

import java.util.List;

public class CustomRaidData {
    public static final Codec<CustomRaidData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BuiltInRegistries.MOB_EFFECT.byNameCodec().fieldOf("raid_effect").forGetter(customRaidData -> customRaidData.raidEffect),
            UniformInt.CODEC.fieldOf("wave_range").forGetter(customRaidData -> customRaidData.waveRange),
            RaiderData.CODEC.listOf().fieldOf("raid_data").forGetter(customRaidData -> customRaidData.raiders)
    ).apply(instance,CustomRaidData::new));

    private final List<RaiderData> raiders;
    private final UniformInt waveRange;
    private final MobEffect raidEffect;
    //private final DayTime  timeExclusive;

    public CustomRaidData(MobEffect mobEffect,UniformInt waveRange,List<RaiderData> raiderData){
        this.raidEffect = mobEffect;
        this.waveRange = waveRange;
        this.raiders = raiderData;
    }

    public record RaiderData(EntityType<?> entityType,List<Integer> spawnRates){
        public static final Codec<RaiderData> CODEC = RecordCodecBuilder.create(raiderDataInstance -> raiderDataInstance.group(
                BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("entity_type").forGetter(RaiderData::entityType),
                Codec.INT.listOf().fieldOf("spawnRates").forGetter(RaiderData::spawnRates)
        ).apply(raiderDataInstance,RaiderData::new));
    }

    enum DayTime implements StringRepresentable {
        DAY("day"),
        NIGHT("night"),
        NONE("none");

        String id;

        DayTime(String id){
            this.id = id;
        }

        @Override
        public String getSerializedName() {
            return this.id;
        }
    }

}
