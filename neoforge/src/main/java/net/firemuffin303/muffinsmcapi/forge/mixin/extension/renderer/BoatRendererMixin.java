package net.firemuffin303.muffinsmcapi.forge.mixin.extension.renderer;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.api.BoatRegistry;
import net.firemuffin303.muffinsmcapi.forge.MuffinsOvenAPINeoForge;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.IOvenBoat;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatUtil;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.client.OvenBoatRenderer;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BoatRenderer.class)
public abstract class BoatRendererMixin {
    @Unique
    private OvenBoatRenderer ovenBoatRenderer;


    @WrapOperation(method = "render(Lnet/minecraft/world/entity/vehicle/Boat;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/BoatRenderer;getModelWithLocation(Lnet/minecraft/world/entity/vehicle/Boat;)Lcom/mojang/datafixers/util/Pair;"))
    public Pair<ResourceLocation, ListModel<Boat>> muffins$redirectRendering(BoatRenderer instance, Boat boat, Operation<Pair<ResourceLocation, ListModel<Boat>>> original){
        if(BuiltInRegistries.REGISTRY.containsKey(MuffinsMcAPI.modid("oven_boat_type"))){
            IOvenBoat iOvenBoat = (IOvenBoat)boat;
            if(BoatRegistry.OVEN_BOAT_REGISTRY.containsKey(ResourceLocation.tryParse(iOvenBoat.getBoatVariantString()))){
                return ovenBoatRenderer.getTextureAndModel(iOvenBoat.getOvenBoatVariant().get());
            }
        }


        return original.call(instance,boat);
    }



    @Inject(method = "<init>",at = @At("TAIL"))
    public void muffins$init(EntityRendererProvider.Context context, boolean bl, CallbackInfo ci){
        if(BuiltInRegistries.REGISTRY.containsKey(MuffinsMcAPI.modid("oven_boat_type"))){
            this.ovenBoatRenderer = new OvenBoatRenderer(context,bl);
        }
    }

    @ModifyReturnValue(method = "getTextureLocation(Lnet/minecraft/world/entity/vehicle/Boat;)Lnet/minecraft/resources/ResourceLocation;",at = @At("RETURN"))
    public ResourceLocation muffins$getTextureLocation(ResourceLocation original,@Local(argsOnly = true) Boat boat){
        IOvenBoat iOvenBoat = (IOvenBoat)boat;
        LogUtils.getLogger().info("{}",BoatRegistry.OVEN_BOAT_REGISTRY.containsKey(ResourceLocation.tryParse(iOvenBoat.getBoatVariantString())));
        if(BoatRegistry.OVEN_BOAT_REGISTRY.containsKey(ResourceLocation.tryParse(iOvenBoat.getBoatVariantString())) ){
            return ovenBoatRenderer.getTextureLocation(iOvenBoat);
        }
        return original;
    }
}
