package net.firemuffin303.muffinsmcapi.fabric.common.multipart;

import net.minecraft.world.entity.Entity;

import java.util.List;

public interface IMultiPartEntity<T extends Entity> {

    List<PartEntity<T>> getParts();
}
