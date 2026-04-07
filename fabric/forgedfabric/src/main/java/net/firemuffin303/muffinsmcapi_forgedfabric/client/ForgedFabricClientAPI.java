package net.firemuffin303.muffinsmcapi_forgedfabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.firemuffin303.muffinsmcapi_forgedfabric.client.animationloader.AnimationLoaderManager;
import net.minecraft.server.packs.PackType;

public class ForgedFabricClientAPI implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(new AnimationLoaderManager());
    }
}
