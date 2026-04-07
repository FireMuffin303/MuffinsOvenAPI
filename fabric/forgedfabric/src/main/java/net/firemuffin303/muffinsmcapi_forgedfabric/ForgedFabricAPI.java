package net.firemuffin303.muffinsmcapi_forgedfabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.firemuffin303.muffinsmcapi_forgedfabric.common.BiomeModifiers;
import net.firemuffin303.muffinsmcapi_forgedfabric.common.GlobalLootModifier;
import net.firemuffin303.muffinsmcapi_forgedfabric.common.GlobalLootTableModifierManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;

public class ForgedFabricAPI implements ModInitializer {

    public static final String MODID = "muffinsmcapi_forgedfabric";

    @Override
    public void onInitialize() {
        GlobalLootModifier.init();
        BiomeModifiers.init();

        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new GlobalLootTableModifierManager());
    }

    public static ResourceLocation modid(String id){
        return ResourceLocation.fromNamespaceAndPath(MODID,id);
    }
}
