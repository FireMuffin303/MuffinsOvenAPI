package net.firemuffin303.muffinsmcapi.forge.mixin.extension.renderer;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.datafixers.util.Pair;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.IOvenBoat;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.client.OvenBoatRenderer;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BoatRenderer.class)
public abstract class BoatRendererMixin {

    @ModifyReturnValue(method = "getModelWithLocation",at = @At("RETURN"))
    public Pair<ResourceLocation, ListModel<Boat>> muffins$redirectRendering(Pair<ResourceLocation, ListModel<Boat>> original, @Local(argsOnly = true) Boat boat){
        if(boat instanceof IOvenBoat ovenBoat && ((BoatRenderer)(Object)this) instanceof OvenBoatRenderer ovenBoatRenderer){
            return ovenBoatRenderer.getTextureAndModel(ovenBoat.getOvenBoatVariant().get());
        }

        return original;
    }
}
