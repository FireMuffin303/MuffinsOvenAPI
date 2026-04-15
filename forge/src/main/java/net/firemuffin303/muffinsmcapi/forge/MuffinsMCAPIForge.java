package net.firemuffin303.muffinsmcapi.forge;

import com.mojang.logging.LogUtils;
import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.api.BlockEntityTypeUtil;
import net.firemuffin303.muffinsmcapi.forge.common.ModBlocks;
import net.firemuffin303.muffinsmcapi.forge.common.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = MuffinsMcAPI.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(MuffinsMcAPI.MOD_ID)
public class MuffinsMCAPIForge {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,MuffinsMcAPI.MOD_ID);

    public MuffinsMCAPIForge(){
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MuffinsMcAPI.init();

        ModBlocks.BLOCK.register(eventBus);
        ModItems.ITEMS.register(eventBus);
        ENTITY_TYPE.register(eventBus);
        eventBus.register(this);
    }

    @SubscribeEvent
    public static void registerPostInit(FMLCommonSetupEvent event){
        event.enqueueWork(() -> {
            BlockEntityTypeUtil.implementBlockEntityType();
        });
    }


}
