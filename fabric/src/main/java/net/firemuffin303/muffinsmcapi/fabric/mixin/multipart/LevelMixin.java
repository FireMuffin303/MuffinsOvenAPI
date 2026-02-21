package net.firemuffin303.muffinsmcapi.fabric.mixin.multipart;

import net.firemuffin303.muffinsmcapi.fabric.common.multipart.IMultiPartEntity;
import net.firemuffin303.muffinsmcapi.fabric.common.multipart.PartEntity;
import net.minecraft.util.AbortableIterationConsumer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityTypeTest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.Predicate;

@Mixin(Level.class)
public abstract class LevelMixin {
    @Inject(method = "method_31593",at = @At("TAIL"))
    private static void muffins$addMultipart(Entity entity, Predicate<? super Entity> predicate, List<Entity> list, Entity entity2, CallbackInfo ci){
        if(entity2 instanceof IMultiPartEntity<?> multiPartEntity){
            for(PartEntity<?> partEntity : multiPartEntity.getParts()){
                if(entity2 != entity && predicate.test(partEntity)){
                    list.add(partEntity);
                }
            }
        }
    }

    @Inject(method = "method_47576",at = @At("TAIL"), cancellable = true)
    private static <T extends Entity> void  muffins$getMultiPart(Predicate<? super T> predicate, List<? super T> list, int i, EntityTypeTest<Entity,T> entityTypeTest, Entity entity, CallbackInfoReturnable<AbortableIterationConsumer.Continuation> cir){
        if(entity instanceof IMultiPartEntity<?> multiPartEntity){
            for(PartEntity<?> partEntity : multiPartEntity.getParts()){
                T entity2 = entityTypeTest.tryCast(partEntity);
                if(entity2 != null && predicate.test(entity2)){
                    list.add(entity2);
                    if (list.size() >= i) {
                        cir.setReturnValue(AbortableIterationConsumer.Continuation.ABORT);
                    }
                }
            }
        }
    }

}
