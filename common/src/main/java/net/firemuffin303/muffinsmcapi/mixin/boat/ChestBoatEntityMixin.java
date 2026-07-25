package net.firemuffin303.muffinsmcapi.mixin.boat;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.IOvenBoat;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ChestBoat.class)
public abstract class ChestBoatEntityMixin {

    @ModifyReturnValue(method = "getDropItem",at = @At("RETURN"))
    public Item muffins$getDropItem(Item original){
        IOvenBoat iOvenBoat = (IOvenBoat)((Object)this);
        OvenBoatVariant ovenBoatVariant = iOvenBoat.getOvenBoatVariant().orElse(null);
        if(ovenBoatVariant != null){
            return ovenBoatVariant.chestBoatItem().get();
        }
        return original;
    }
}
