package net.firemuffin303.muffinsmcapi.neoforge;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.api.CameraAPI;
import net.firemuffin303.muffinsmcapi.api.customRaid.CustomRaidRegistry;
import net.firemuffin303.muffinsmcapi.common.data.DripstoneDataManager;
import net.firemuffin303.muffinsmcapi.common.data.RaidDataManager;
import net.firemuffin303.muffinsmcapi.neoforge.client.data.RaidProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@Mod(MuffinsMcAPI.MOD_ID)
public final class MuffinsMCAPINeoForge {
    public MuffinsMCAPINeoForge(IEventBus eventBus) {
        // Run our common setup.
        MuffinsMcAPI.init();

        NeoForge.EVENT_BUS.addListener(MuffinsMCAPINeoForge::registerCommand);
        NeoForge.EVENT_BUS.addListener(MuffinsMCAPINeoForge::onRegisterReloadListeners);
        eventBus.addListener(MuffinsMCAPINeoForge::dataGen);
    }

    private static void dataGen(GatherDataEvent event){
        DataGenerator dataGenerator = event.getGenerator();
        PackOutput packOutput = dataGenerator.getPackOutput();

        event.addProvider(new RaidProvider(packOutput,event.getLookupProvider()));
    }

    private static void registerCommand(RegisterCommandsEvent commandsEvent){
        CameraAPI.registerCommand(commandsEvent.getDispatcher(),commandsEvent.getBuildContext(),commandsEvent.getCommandSelection());
        CustomRaidRegistry.registerCommand(commandsEvent.getDispatcher(),commandsEvent.getBuildContext(),commandsEvent.getCommandSelection());
    }

    private static void onRegisterReloadListeners(AddReloadListenerEvent event) {
        HolderLookup.Provider provider = event.getRegistryAccess();
        event.addListener(new RaidDataManager(provider));
        event.addListener(new DripstoneDataManager(provider));
    }


}
