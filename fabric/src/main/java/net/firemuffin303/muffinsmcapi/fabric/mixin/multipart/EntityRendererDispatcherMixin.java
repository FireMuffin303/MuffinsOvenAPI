package net.firemuffin303.muffinsmcapi.fabric.mixin.multipart;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.fabric.impl.client.rendering.EntityRendererRegistryImpl;
import net.firemuffin303.muffinsmcapi.fabric.common.multipart.impl.EntityRenderDispatcherImpl;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public abstract class EntityRendererDispatcherMixin {

    @Inject(method = "renderHitbox", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/LevelRenderer;renderLineBox(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;Lnet/minecraft/world/phys/AABB;FFFF)V", ordinal = 0, shift = At.Shift.AFTER))
    private static void muffins$renderPartEntity(PoseStack poseStack, VertexConsumer vertexConsumer, Entity entity, float f, float g, float h, float i, CallbackInfo ci) {
        EntityRenderDispatcherImpl.renderPartHitBox(poseStack, vertexConsumer, entity, f);
    }
}