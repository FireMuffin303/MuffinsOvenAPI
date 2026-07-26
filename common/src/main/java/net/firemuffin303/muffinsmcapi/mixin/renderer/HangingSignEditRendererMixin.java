package net.firemuffin303.muffinsmcapi.mixin.renderer;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.muffinsmcapi.impl.block.OvenSign;
import net.firemuffin303.muffinsmcapi.util.PlatformUtil;
import net.minecraft.client.gui.screens.inventory.HangingSignEditScreen;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HangingSignEditScreen.class)
public abstract class HangingSignEditRendererMixin {
    @Mutable
    @Shadow @Final private ResourceLocation texture;

    @Inject(method = "<init>",at = @At("TAIL"))
    public void muffins$init(SignBlockEntity signBlockEntity, boolean bl, boolean bl2, CallbackInfo ci){
        if(signBlockEntity.getBlockState().getBlock() instanceof OvenSign ovenSign){
            ResourceLocation resourceLocation = PlatformUtil.getBlock(signBlockEntity.getBlockState().getBlock());
            this.texture = (ResourceLocation.fromNamespaceAndPath(resourceLocation.getNamespace(),ovenSign.getSignID() + ".png")).withPrefix("textures/gui/hanging_signs/");
        }
    }


}
