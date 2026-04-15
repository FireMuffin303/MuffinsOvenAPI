package net.firemuffin303.muffinsmcapi.mixin.renderer;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.muffinsmcapi.impl.block.OvenSign;
import net.firemuffin303.muffinsmcapi.util.PlatformUtil;
import net.minecraft.client.gui.screens.inventory.SignEditScreen;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SignEditScreen.class)
public abstract class SignEditScreenMixin {
    @Shadow protected abstract void init();

    @ModifyExpressionValue(method = "renderSignBackground",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Sheets;getSignMaterial(Lnet/minecraft/world/level/block/state/properties/WoodType;)Lnet/minecraft/client/resources/model/Material;"))
    public Material muffins$getTexture(Material original, @Local(argsOnly = true)BlockState blockState){
        if(blockState.getBlock() instanceof OvenSign ovenSign){
            ResourceLocation resourceLocation = PlatformUtil.getBlock(blockState.getBlock());
            return new Material(Sheets.SIGN_SHEET,new ResourceLocation(resourceLocation.getNamespace(),"entity/signs/" + ovenSign.getSignID()));
        }
        return original;
    }
}
