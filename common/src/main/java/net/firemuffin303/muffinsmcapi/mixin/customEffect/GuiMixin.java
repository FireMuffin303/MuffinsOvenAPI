package net.firemuffin303.muffinsmcapi.mixin.customEffect;

import com.mojang.blaze3d.systems.RenderSystem;
import net.firemuffin303.muffinsmcapi.api.CustomEffectRegistry;
import net.firemuffin303.muffinsmcapi.impl.customEffect.CustomEffectImpl;
import net.firemuffin303.muffinsmcapi.impl.customEffect.CustomEffectRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.stream.Collectors;

@Mixin(Gui.class)
public class GuiMixin {

    @Shadow @Final
    private Minecraft minecraft;

    @Inject(method = "renderEffects",at = @At(value = "HEAD"))
    public void muffins$renderSpecialEffect(GuiGraphics guiGraphics, CallbackInfo ci){
        Collection<CustomEffectRenderer> clist = CustomEffectRegistry.getEffects().stream().filter(customEffectRenderer -> customEffectRenderer.shouldRender(minecraft.player)).collect(Collectors.toSet());

        if(!clist.isEmpty() && !(this.minecraft.screen instanceof EffectRenderingInventoryScreen<?> effectRenderingInventoryScreen && effectRenderingInventoryScreen.canSeeEffects()) ){
            RenderSystem.enableBlend();
            int yPos = 53;
            int xOffset = 0;
            for(CustomEffectRenderer customEffectRenderer: clist){
                int xPos = guiGraphics.guiWidth();
                xOffset++;
                xPos -= 25 * xOffset;
                CustomEffectImpl.specialEffectRenderHUDBackground(guiGraphics,xPos,yPos,customEffectRenderer.color(minecraft.player),customEffectRenderer.iconTexture(minecraft.player));
            }
        }
    }
}
