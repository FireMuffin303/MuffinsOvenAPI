package net.firemuffin303.muffinsmcapi.fabric.mixin.multipart;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.firemuffin303.muffinsmcapi.fabric.common.multipart.PartEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @Definition(id = "EnderDragonPart", type = EnderDragonPart.class)
    @Expression("? instanceof EnderDragonPart")
    @Inject(method = "attack", at = @At("MIXINEXTRAS:EXPRESSION"))
    public void muffins$checkIfMultipart(Entity entity, CallbackInfo ci, @Local(ordinal = 1) LocalRef<Entity> entity2){
        if(entity instanceof PartEntity<?> partEntity){
            entity2.set(partEntity.parentMob);
        }
    }
}
