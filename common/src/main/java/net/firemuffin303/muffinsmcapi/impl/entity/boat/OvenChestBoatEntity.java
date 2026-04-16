package net.firemuffin303.muffinsmcapi.impl.entity.boat;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class OvenChestBoatEntity extends ChestBoat implements IOvenBoat {
    public static final EntityDataAccessor<Optional<OvenBoatVariant>> TYPE = SynchedEntityData.defineId(OvenChestBoatEntity.class,OvenBoatUtil.SERIALIZER);

    public OvenChestBoatEntity(EntityType<? extends Boat> entityType, Level level) {
        super(entityType, level);
    }

    public OvenChestBoatEntity(Level level, double d, double e, double f) {
        this(BoatRegistry.OVEN_CHEST_BOAT.get(), level);
        this.setPos(d, e, f);
        this.xo = d;
        this.yo = e;
        this.zo = f;
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
}
