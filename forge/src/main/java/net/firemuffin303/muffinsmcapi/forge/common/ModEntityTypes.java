package net.firemuffin303.muffinsmcapi.forge.common;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister.create(Registries.ENTITY_TYPE, MuffinsMcAPI.MOD_ID);

}
