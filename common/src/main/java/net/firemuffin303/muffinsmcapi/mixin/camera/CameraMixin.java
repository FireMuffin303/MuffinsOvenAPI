package net.firemuffin303.muffinsmcapi.mixin.camera;

import net.firemuffin303.muffinsmcapi.impl.camera.ICameraShake;
import net.minecraft.client.Camera;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract class CameraMixin implements ICameraShake {

    @Shadow
    protected abstract void setRotation(float f, float g);

    @Shadow private float yRot;
    @Shadow private float xRot;
    @Shadow private float partialTickTime;
    @Unique
    float muffinsmcapi$shakeIntensity = 0.0f;
    @Unique float muffinsmcapi$shakeReductionRate = 0.1f;

    @Inject(method = "setup",at = @At(value = "TAIL"))
    public void muffins$setShake(BlockGetter blockGetter, Entity entity, boolean notFirstPerson, boolean secondPersonView, float deltaTick, CallbackInfo ci){
        if(muffinsmcapi$shakeIntensity > 0){
            float randoX = entity.getRandom().nextFloat() * (entity.getRandom().nextBoolean() ? 1 : -1);
            float randoY = entity.getRandom().nextFloat() * (entity.getRandom().nextBoolean() ? 1 : -1);

            float shakeY = (randoY * (this.muffinsmcapi$shakeIntensity * this.muffinsmcapi$shakeIntensity)) * 0.25f;
            float shakeX = (randoX * (this.muffinsmcapi$shakeIntensity * this.muffinsmcapi$shakeIntensity)) * 0.25f;

            this.setRotation(this.yRot + shakeY,this.xRot + shakeX);
        }
    }

    @Inject(method = "tick",at = @At("TAIL"))
    public void muffins$shakeIntensityReduction(CallbackInfo ci){
        if(this.muffinsmcapi$shakeIntensity > 0){
            this.muffinsmcapi$shakeIntensity = Math.max(this.muffinsmcapi$shakeIntensity - (this.partialTickTime * this.muffinsmcapi$shakeReductionRate) , 0);

            if(this.muffinsmcapi$shakeIntensity <= 0){
                this.muffinsmcapi$shakeReductionRate = 0.1f;
            }
        }
    }

    @Override
    public void muffinsmcapi$setShakeReduction(float reduction) {
        this.muffinsmcapi$shakeReductionRate = reduction;
    }

    @Override
    public void muffinsmcapi$setShakeIntensity(float intensity) {
        this.muffinsmcapi$shakeIntensity = intensity;
    }
}
