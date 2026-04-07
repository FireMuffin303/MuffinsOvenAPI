package net.firemuffin303.muffinsmcapi.common;

import net.firemuffin303.muffinsmcapi.common.item.components.CustomAttackParticleData;
import net.firemuffin303.muffinsmcapi.common.item.components.ItemHoverData;
import net.firemuffin303.muffinsmcapi.util.PlatformUtil;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Unit;

public class MuffinsDataComponents {
    public static final DataComponentType<ItemHoverData> HOVER = register("hover",DataComponentType.<ItemHoverData>builder().persistent(ItemHoverData.CODEC).networkSynchronized(ItemHoverData.DIRECT_STREAM_CODEC));
    public static final DataComponentType<Unit> PERSISTENT = register("persistent",DataComponentType.<Unit>builder().persistent(Unit.CODEC).networkSynchronized(StreamCodec.unit(Unit.INSTANCE)));
    //public static final DataComponentType<CustomAttackParticleData> CUSTOM_ATTACK_PARTICLE_DATA = register("custom_attack_particle_data",DataComponentType.<CustomAttackParticleData>builder());

    public static <T> DataComponentType<T> register(String string, DataComponentType.Builder<T> builder){
        return PlatformUtil.registerDataComponent(string,builder);
    }

    public static void init(){}
}
