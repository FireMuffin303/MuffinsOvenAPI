package net.firemuffin303.muffinsmcapi.mixin.boat;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.IOvenBoat;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Boat.class)
public class BoatEntityMixin {
    @ModifyExpressionValue(method = "checkFallDamage",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/vehicle/Boat$Type;getPlanks()Lnet/minecraft/world/level/block/Block;"))
    public Block muffins$getPlanks(Block original){
        if(this instanceof IOvenBoat iOvenBoat && iOvenBoat.getOvenBoatVariant().isPresent()){
            return iOvenBoat.getOvenBoatVariant().get().planks().get();
        }


        return original;
    }
}
