package net.firemuffin303.muffinsmcapi.fabric.mixin.multipart;

import net.firemuffin303.muffinsmcapi.fabric.common.multipart.IMultiPartEntity;
import net.firemuffin303.muffinsmcapi.fabric.common.multipart.PartEntity;
import net.firemuffin303.muffinsmcapi.fabric.common.multipart.impl.ServerMultiPartAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "net.minecraft.server.level.ServerLevel$EntityCallbacks")
public abstract class ServerEntityCallbacksMixin {

    @Final
    @Shadow
    ServerLevel field_26936;

    @Inject(method = "onTrackingStart(Lnet/minecraft/world/entity/Entity;)V",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;updateDynamicGameEventListener(Ljava/util/function/BiConsumer;)V"))
    public void muffins$trackMultipartStart(Entity entity, CallbackInfo ci){
        if(entity instanceof IMultiPartEntity<?> multiPartEntity){
            for(PartEntity<?> partEntity : multiPartEntity.getParts()){
                ((ServerMultiPartAccessor) field_26936).getPartsMap().put(partEntity.getId(),partEntity);
            }
        }
    }

    @Inject(method = "onTrackingEnd(Lnet/minecraft/world/entity/Entity;)V",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;updateDynamicGameEventListener(Ljava/util/function/BiConsumer;)V"))
    public void muffins$trackMultipartEnd(Entity entity, CallbackInfo ci){
        if(entity instanceof IMultiPartEntity<?> multiPartEntity){
            for(PartEntity<?> partEntity : multiPartEntity.getParts()){
                ((ServerMultiPartAccessor) field_26936).getPartsMap().remove(partEntity.getId());
            }
        }
    }
}
