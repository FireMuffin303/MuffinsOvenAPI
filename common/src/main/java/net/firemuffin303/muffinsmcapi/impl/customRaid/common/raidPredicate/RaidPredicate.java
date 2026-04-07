package net.firemuffin303.muffinsmcapi.impl.customRaid.common.raidPredicate;

import com.mojang.serialization.Codec;

public interface RaidPredicate {

    <T extends RaidPredicate> Codec<T> getCodec();
}
