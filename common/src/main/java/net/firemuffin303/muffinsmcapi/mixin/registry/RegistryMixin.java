package net.firemuffin303.muffinsmcapi.mixin.registry;

import net.firemuffin303.muffinsmcapi.impl.registration.RegistryHolder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Registry.class)
public interface RegistryMixin<T> {
    @ModifyVariable(method = "safeCastToReference", at = @At("HEAD"), argsOnly = true)
    default Holder<T> muffins$resolveHolder(Holder<T> value){
        if(value instanceof RegistryHolder<T> registryHolder){
             return registryHolder.getHolderObject();
        }
        return value;
    }
}
