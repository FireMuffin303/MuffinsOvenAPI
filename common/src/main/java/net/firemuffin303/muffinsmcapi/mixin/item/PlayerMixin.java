package net.firemuffin303.muffinsmcapi.mixin.item;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import net.firemuffin303.muffinsmcapi.impl.item.ICustomAttackParticle;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @WrapWithCondition(method = "attack",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/player/Player;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V",ordinal = 1))
    public boolean muffins$customSweepAttackEffect(Level instance, Player arg, double d, double e, double f, SoundEvent arg2, SoundSource arg3, float g, float h, @Local ItemStack itemStack, @Share("canceledSweepParticle") LocalBooleanRef cancelSweepParticle){
        cancelSweepParticle.set(true);
        if(itemStack.getItem() instanceof ICustomAttackParticle iCustomAttackParticle){
            boolean bl = iCustomAttackParticle.sweepAttackParticle(arg,arg.level());
            cancelSweepParticle.set(bl);
            return bl;
        }
        return true;
    }

    @WrapWithCondition(method = "attack",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;sweepAttack()V"))
    public boolean muffins$checkSweepAttackEffect(Player instance,@Share("canceledSweepParticle") LocalBooleanRef cancelSweepParticle){
        return cancelSweepParticle.get();
    }


    @WrapWithCondition(method = "attack",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/player/Player;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V",ordinal = 2))
    public boolean muffins$customSweepAttackEffect(Level instance, Player arg, double d, double e, double f, SoundEvent arg2, SoundSource arg3, float g, float h, @Local ItemStack itemStack){
        if(itemStack.getItem() instanceof ICustomAttackParticle iCustomAttackParticle){
           return iCustomAttackParticle.critAttackEffect(arg,arg.level());
        }
        return true;
    }

    @WrapWithCondition(method = "attack",at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/player/Player;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V",ordinal = 3))
    public boolean muffins$customStrongAttackEffect(Level instance, Player arg, double d, double e, double f, SoundEvent arg2, SoundSource arg3, float g, float h, @Local ItemStack itemStack){
        if(itemStack.getItem() instanceof ICustomAttackParticle iCustomAttackParticle){
            return iCustomAttackParticle.strongAttackEffect(instance,new Vec3(d, e, f),arg3);
        }
        return true;
    }

    @WrapWithCondition(method = "attack",at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/player/Player;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V",ordinal = 4))
    public boolean muffins$customWeakAttackEffect(Level instance, Player arg, double d, double e, double f, SoundEvent arg2, SoundSource arg3, float g, float h,@Local ItemStack itemStack){
        if(itemStack.getItem() instanceof ICustomAttackParticle iCustomAttackParticle){
             return iCustomAttackParticle.weakAttackEffect(instance,new Vec3(d, e, f),arg3);
        }
        return true;
    }
}
