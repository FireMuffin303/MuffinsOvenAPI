package net.firemuffin303.muffinsmcapi.forge.mixin.extension.renderer;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.datafixers.util.Pair;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.IOvenBoat;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.client.OvenBoatRenderer;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Debug(export = true)
@Mixin(BoatRenderer.class)
public abstract class BoatRendererMixin {

    @WrapOperation(method = "render(Lnet/minecraft/world/entity/vehicle/Boat;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/BoatRenderer;getModelWithLocation(Lnet/minecraft/world/entity/vehicle/Boat;)Lcom/mojang/datafixers/util/Pair;"))
    public Pair<ResourceLocation, ListModel<Boat>> muffins$redirectRendering(BoatRenderer instance, Boat boat, Operation<Pair<ResourceLocation, ListModel<Boat>>> original){
        if(boat instanceof IOvenBoat ovenBoat && ((BoatRenderer)(Object)this) instanceof OvenBoatRenderer ovenBoatRenderer){
            return ovenBoatRenderer.getTextureAndModel(ovenBoat.getOvenBoatVariant().get());
        }

        return original.call(instance, boat);
    }
}
