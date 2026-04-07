package net.firemuffin303.muffinsmcapi.fabric.common.datamaps;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class NeoforgeDataMapLoader implements PreparableReloadListener, IdentifiableResourceReloadListener {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();


    @Override
    public CompletableFuture<Void> reload(PreparationBarrier preparationBarrier, ResourceManager resourceManager, ProfilerFiller profilerFiller, ProfilerFiller profilerFiller2, Executor executor, Executor executor2) {
        scanFile(resourceManager);

        //Compostable.COMPOSTABLES.put()
        return null;
    }



    private static void scanFile(ResourceManager resourceManager){
        Map<ResourceLocation,Resource> dataMaps = resourceManager.listResources("data_maps", resourceLocation -> {
            return resourceLocation.getPath().endsWith(".json");
        });

        dataMaps.forEach((resourceLocation, resource) -> {

        });

    }

    @Override
    public ResourceLocation getFabricId() {
        return MuffinsMcAPI.modid("neoforge_datamap");
    }
}
