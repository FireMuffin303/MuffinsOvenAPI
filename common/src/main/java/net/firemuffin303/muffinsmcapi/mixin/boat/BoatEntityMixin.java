package net.firemuffin303.muffinsmcapi.mixin.boat;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.IOvenBoat;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatUtil;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(Boat.class)
public abstract class BoatEntityMixin extends Entity implements IOvenBoat {
    @Unique
    private static final EntityDataAccessor<String> DATA_CUSTOM_TYPE = SynchedEntityData.defineId(Boat.class, EntityDataSerializers.STRING);

    public BoatEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "defineSynchedData",at = @At("TAIL"))
    public void muffinsSynchedData(CallbackInfo ci, @Local(argsOnly = true) SynchedEntityData.Builder builder){
        builder.define(DATA_CUSTOM_TYPE,"");
    }

    @Inject(method = "addAdditionalSaveData",at = @At(value ="TAIL"))
    public void muffins$addCustomType(CompoundTag compoundTag, CallbackInfo ci){
        compoundTag.putString("oven_type",this.entityData.get(DATA_CUSTOM_TYPE));
    }

    @Inject(method = "readAdditionalSaveData",at = @At(value ="TAIL"))
    public void muffins$readCustomType(CompoundTag compoundTag, CallbackInfo ci){
        if(compoundTag.contains("oven_type",8)){
            this.entityData.set(DATA_CUSTOM_TYPE,compoundTag.getString("oven_type"));
        }
    }


    @ModifyExpressionValue(method = "checkFallDamage",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/vehicle/Boat$Type;getPlanks()Lnet/minecraft/world/level/block/Block;"))
    public Block muffins$getPlanks(Block original){
        if(this.getOvenBoatVariant().isPresent()){
            return this.getOvenBoatVariant().get().planks().get();
        }


        return original;
    }

    @ModifyReturnValue(method = "getDropItem",at = @At("RETURN"))
    public Item muffins$getDropItem(Item original){
        OvenBoatVariant ovenBoatVariant = this.getOvenBoatVariant().orElse(null);
        if(ovenBoatVariant != null){
            return ovenBoatVariant.boatItem().get();
        }
        return original;
    }

    @Override
    public void setVariant(OvenBoatVariant variant) {
        this.entityData.set(DATA_CUSTOM_TYPE, OvenBoatUtil.getBoatKey(variant).toString());
    }

    @Override
    public Optional<OvenBoatVariant> getOvenBoatVariant() {
        OvenBoatVariant ovenBoatVariant = OvenBoatUtil.getBoat(ResourceLocation.tryParse(this.entityData.get(DATA_CUSTOM_TYPE)));
        if(this.entityData.get(DATA_CUSTOM_TYPE).isEmpty() || ovenBoatVariant == null){
            return Optional.empty();
        }

        return Optional.of(ovenBoatVariant);
    }

    @Override
    public String getBoatVariantString() {
        return this.entityData.get(DATA_CUSTOM_TYPE);
    }
}
