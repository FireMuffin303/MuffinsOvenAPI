package net.firemuffin303.muffinsmcapi.fabric.common.multipart.impl;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.firemuffin303.muffinsmcapi.fabric.common.multipart.IMultiPartEntity;
import net.firemuffin303.muffinsmcapi.fabric.common.multipart.PartEntity;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class EntityRenderDispatcherImpl {

    public static void renderPartHitBox(PoseStack poseStack, VertexConsumer vertexConsumer, Entity entity, float f){
        if(entity instanceof IMultiPartEntity<?> multiPartEntity){
            double d = -Mth.lerp(f, entity.xOld, entity.getX());
            double e = -Mth.lerp(f, entity.yOld, entity.getY());
            double j = -Mth.lerp(f, entity.zOld, entity.getZ());

            for(PartEntity<?> partEntity : multiPartEntity.getParts()){
                poseStack.pushPose();
                double k = d + Mth.lerp(f, partEntity.xOld, partEntity.getX());
                double l = e + Mth.lerp(f, partEntity.yOld, partEntity.getY());
                double m = j + Mth.lerp(f, partEntity.zOld, partEntity.getZ());
                poseStack.translate(k, l, m);

                LevelRenderer.renderLineBox(
                        poseStack,
                        vertexConsumer,
                        partEntity.getBoundingBox().move(-partEntity.getX(), -partEntity.getY(), -partEntity.getZ()),
                        0.25F,
                        1.0F,
                        0.0F,
                        1.0F
                );
                poseStack.popPose();
            }
        }
    }
}
