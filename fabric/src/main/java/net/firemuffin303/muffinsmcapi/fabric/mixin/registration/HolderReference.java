package net.firemuffin303.muffinsmcapi.fabric.mixin.registration;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Holder.Reference.class)
public class HolderReference<T> {

    @Shadow @Nullable private ResourceKey<T> key;

    @Unique
    @Override
    public int hashCode() {
        return this.key.hashCode();
    }
}
