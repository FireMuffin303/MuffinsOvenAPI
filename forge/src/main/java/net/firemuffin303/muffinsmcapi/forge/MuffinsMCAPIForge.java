package net.firemuffin303.muffinsmcapi.forge;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.api.BlockEntityTypeUtil;
import net.firemuffin303.muffinsmcapi.forge.common.ModBlocks;
import net.firemuffin303.muffinsmcapi.forge.common.ModBoatVariants;
import net.firemuffin303.muffinsmcapi.forge.common.ModEntityTypes;
import net.firemuffin303.muffinsmcapi.forge.common.ModItems;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.BoatRegistry;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.*;
import org.apache.http.config.Registry;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = MuffinsMcAPI.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(MuffinsMcAPI.MOD_ID)
public class MuffinsMCAPIForge {

    public MuffinsMCAPIForge(){
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MuffinsMcAPI.init();

        ModBlocks.BLOCK.register(eventBus);
        ModItems.ITEMS.register(eventBus);
        ModEntityTypes.ENTITY_TYPE.register(eventBus);
        ModBoatVariants.OVEN_BOAT_VARIANT.register(eventBus);
        eventBus.register(this);
    }

    @SubscribeEvent
    public static void registerPostInit(FMLCommonSetupEvent event){
        event.enqueueWork(BlockEntityTypeUtil::implementBlockEntityType);
    }
}
