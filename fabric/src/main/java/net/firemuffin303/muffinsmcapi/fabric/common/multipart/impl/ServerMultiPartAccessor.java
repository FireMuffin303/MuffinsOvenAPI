package net.firemuffin303.muffinsmcapi.fabric.common.multipart.impl;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.firemuffin303.muffinsmcapi.fabric.common.multipart.PartEntity;

public interface ServerMultiPartAccessor {

    Int2ObjectMap<PartEntity<?>> getPartsMap();
}
