package net.firemuffin303.muffinsmcapi.mixin.renderer;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import net.firemuffin303.muffinsmcapi.api.block.sign.OvenStandSignBlock;
import net.firemuffin303.muffinsmcapi.api.block.sign.OvenWallSignBlock;
import net.firemuffin303.muffinsmcapi.impl.block.OvenSign;
import net.firemuffin303.muffinsmcapi.util.PlatformUtil;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SignRenderer.class)
public abstract class SignRendererMixin {
    @Unique
    private Block muffins$cacheBlock;

    @Inject(method = "render(Lnet/minecraft/world/level/block/entity/SignBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V",at = @At(value = "INVOKE", target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"))
    public void muffins$render(SignBlockEntity signBlockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j, CallbackInfo ci){
        Block block = signBlockEntity.getBlockState().getBlock();
        if(block instanceof OvenSign){
            this.muffins$cacheBlock = block;
        }
    }

    @ModifyReturnValue(method = "getSignMaterial",at = @At("RETURN"))
    public Material getSignMaterial(Material original){
        if(this.muffins$cacheBlock instanceof OvenSign ovenSign){
            ResourceLocation resourceLocation = PlatformUtil.getBlock(this.muffins$cacheBlock);
            this.muffins$cacheBlock = null;
            return new Material(Sheets.SIGN_SHEET, new ResourceLocation(resourceLocation.getNamespace(),"entity/signs/" + ovenSign.getSignID()));
        }
        return original;
    }
}
