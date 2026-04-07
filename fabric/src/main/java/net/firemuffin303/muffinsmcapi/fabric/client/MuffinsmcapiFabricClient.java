package net.firemuffin303.muffinsmcapi.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.firemuffin303.muffinsmcapi.client.MuffinMcAPIClient;

public final class MuffinsmcapiFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MuffinMcAPIClient.init();

    }
}
