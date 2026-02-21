package net.firemuffin303.muffinsmcapi.neoforge;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.api.CameraAPI;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@Mod(MuffinsMcAPI.MOD_ID)
public final class MuffinsMCAPINeoForge {
    public MuffinsMCAPINeoForge(IEventBus eventBus) {
        // Run our common setup.
        MuffinsMcAPI.init();

        NeoForge.EVENT_BUS.addListener(MuffinsMCAPINeoForge::registerCommand);

    }


    private static void registerCommand(RegisterCommandsEvent commandsEvent){
        CameraAPI.registerCommand(commandsEvent.getDispatcher(),commandsEvent.getBuildContext(),commandsEvent.getCommandSelection());
    }
}
