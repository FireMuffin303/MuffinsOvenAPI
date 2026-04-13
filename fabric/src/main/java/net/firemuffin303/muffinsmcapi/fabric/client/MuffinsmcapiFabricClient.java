package net.firemuffin303.muffinsmcapi.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.firemuffin303.muffinsmcapi.api.CustomEffectRegistry;
import net.firemuffin303.muffinsmcapi.client.MuffinMcAPIClient;
import net.firemuffin303.muffinsmcapi.impl.customEffect.CustomEffectRenderer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public final class MuffinsmcapiFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MuffinMcAPIClient.init();
    }
}
