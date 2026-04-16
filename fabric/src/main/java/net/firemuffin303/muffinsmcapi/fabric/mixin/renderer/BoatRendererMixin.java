package net.firemuffin303.muffinsmcapi.fabric.mixin.renderer;

import com.mojang.datafixers.util.Pair;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.IOvenBoat;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.client.OvenBoatRenderer;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Map;

@Mixin(BoatRenderer.class)
public abstract class BoatRendererMixin {

    @Redirect(method = "render(Lnet/minecraft/world/entity/vehicle/Boat;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",at = @At(value = "INVOKE", target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"))
    public Object muffins$redirectRendering(Map<Boat.Type, Pair<ResourceLocation, ListModel<Boat>>> instance, Object object,Boat boat){
        if(boat instanceof IOvenBoat ovenBoat && ((BoatRenderer)(Object)this) instanceof OvenBoatRenderer ovenBoatRenderer){
            return ovenBoatRenderer.getTextureAndModel(ovenBoat.getOvenBoatVariant().get());
        }

        return instance.get(object);
    }
}
