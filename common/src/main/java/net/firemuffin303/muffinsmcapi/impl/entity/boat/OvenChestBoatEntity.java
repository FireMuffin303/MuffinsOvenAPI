package net.firemuffin303.muffinsmcapi.impl.entity.boat;

import net.firemuffin303.muffinsmcapi.api.BoatRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class OvenChestBoatEntity extends ChestBoat implements IOvenBoat {
    public static final EntityDataAccessor<Optional<OvenBoatVariant>> TYPE = SynchedEntityData.defineId(OvenChestBoatEntity.class,OvenBoatUtil.SERIALIZER);

    public OvenChestBoatEntity(EntityType<? extends Boat> entityType, Level level) {
        super(entityType, level);
    }

    public OvenChestBoatEntity(Level level, double d, double e, double f) {
        this(OvenBoatUtil.OVEN_CHEST_BOAT.get(), level);
        this.setPos(d, e, f);
        this.xo = d;
        this.yo = e;
        this.zo = f;
    }

    @Override
    public Item getDropItem() {
        if(this.getOvenBoatVariant().isPresent()){
            return this.getOvenBoatVariant().get().chestBoatItem().get();
        }

        return super.getDropItem();
    }

    public void tick() {
        if (this.getOvenBoatVariant().isPresent()) {
            super.tick();
        } else {
            this.discard();
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TYPE,Optional.empty());
    }

    @Override
    public void setVariant(OvenBoatVariant variant) {
        this.entityData.set(TYPE,Optional.of(variant));
    }

    @Override
    public Optional<OvenBoatVariant> getOvenBoatVariant() {
        return this.entityData.get(TYPE);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        if(this.getOvenBoatVariant().isPresent()){
            ResourceLocation resourceLocation = OvenBoatUtil.getBoatKey(this.getOvenBoatVariant().get());
            compoundTag.putString("oven_boat_type",resourceLocation.toString());
        }
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        if(!compoundTag.contains("oven_boat_type")){
            this.discard();
        }

        OvenBoatVariant ovenBoatVariant = OvenBoatUtil.getBoat(new ResourceLocation(compoundTag.getString("oven_boat_type")));
        this.setVariant(ovenBoatVariant);
    }
}
