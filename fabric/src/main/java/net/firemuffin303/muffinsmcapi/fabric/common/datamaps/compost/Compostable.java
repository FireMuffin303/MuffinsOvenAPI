package net.firemuffin303.muffinsmcapi.fabric.common.datamaps.compost;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

public record Compostable(float chance, boolean canVillagerCompost) {
    public static final Codec<Compostable> CHANCE_CODEC = Codec.floatRange(0f, 1f)
            .xmap(Compostable::new, Compostable::chance);
    public static final Codec<Compostable> CODEC = Codec.withAlternative(
            RecordCodecBuilder.create(in -> in.group(
                    Codec.floatRange(0f, 1f).fieldOf("chance").forGetter(Compostable::chance),
                    Codec.BOOL.optionalFieldOf("can_villager_compost", false).forGetter(Compostable::canVillagerCompost)).apply(in, Compostable::new)),
            CHANCE_CODEC);


    public static Map<Item, Compostable> COMPOSTABLES = new HashMap<>();

    public Compostable(float chance){
        this(chance,false);
    }

    public static void init(){
        COMPOSTABLES.forEach((item, compostable) -> {
            CompostingChanceRegistry.INSTANCE.add(item, compostable.chance());
        });


    }
}
