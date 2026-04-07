package net.firemuffin303.muffinsmcapi.impl.customRaid.common.raidPredicate;

import com.mojang.serialization.Codec;

public class TimeRaidPredicate implements RaidPredicate{

    @Override
    public <T extends RaidPredicate> Codec<T> getCodec() {
        return null;
    }
}
