package net.firemuffin303.muffinsmcapi.fabric.mixin.renderer;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.logging.LogUtils;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.IOvenBoat;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.client.OvenBoatRenderer;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Debug(export = true)
@Mixin(BoatRenderer.class)
public abstract class BoatRendererMixin {
    @Unique
    private OvenBoatRenderer ovenBoatRenderer;

    @Inject(method = "<init>",at = @At("TAIL"))
    public void muffins$init(EntityRendererProvider.Context context, boolean bl, CallbackInfo ci){
        this.ovenBoatRenderer = new OvenBoatRenderer(context,bl);
    }


    @WrapOperation(method = "render(Lnet/minecraft/world/entity/vehicle/Boat;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",at = @At(value = "INVOKE", target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"))
    public Object muffins$redirectRendering(Map instance, Object object, Operation<Object> original, @Local(argsOnly = true) Boat boat){
        IOvenBoat iOvenBoat = (IOvenBoat)boat;
        if( iOvenBoat.getOvenBoatVariant().isPresent() ){
            return ovenBoatRenderer.getTextureAndModel(iOvenBoat.getOvenBoatVariant().get());
        }

        return instance.get(object);
    }

    @ModifyReturnValue(method = "getTextureLocation(Lnet/minecraft/world/entity/vehicle/Boat;)Lnet/minecraft/resources/ResourceLocation;",at = @At("RETURN"))
    public ResourceLocation muffins$getTextureLocation(ResourceLocation original,@Local(argsOnly = true) Boat boat){
        IOvenBoat iOvenBoat = (IOvenBoat)boat;
        if( iOvenBoat.getOvenBoatVariant().isPresent() ){
            return ovenBoatRenderer.getTextureLocation(iOvenBoat);
        }
        return original;
    }
}
