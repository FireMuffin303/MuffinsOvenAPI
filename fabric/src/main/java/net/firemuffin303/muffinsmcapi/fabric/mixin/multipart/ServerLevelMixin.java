package net.firemuffin303.muffinsmcapi.fabric.mixin.multipart;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.firemuffin303.muffinsmcapi.fabric.common.multipart.PartEntity;
import net.firemuffin303.muffinsmcapi.fabric.common.multipart.impl.ServerMultiPartAccessor;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin implements ServerMultiPartAccessor {
    @Unique
    final Int2ObjectMap<PartEntity<?>> parts = new Int2ObjectArrayMap<>();

    @Override
    public Int2ObjectMap<PartEntity<?>> getPartsMap() {
        return parts;
    }


}
