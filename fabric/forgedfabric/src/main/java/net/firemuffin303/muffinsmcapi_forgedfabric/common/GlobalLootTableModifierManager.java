package net.firemuffin303.muffinsmcapi_forgedfabric.common;

import com.google.gson.JsonElement;
import com.mojang.serialization.*;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi_forgedfabric.loot_modifiers.IGlobalLootModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Unit;
import net.minecraft.util.profiling.ProfilerFiller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class GlobalLootTableModifierManager implements PreparableReloadListener, IdentifiableResourceReloadListener {
    public static final List<IGlobalLootModifier> GLOBAL_LOOT_MODIFIERS = new ArrayList<>();


    @Override
    public CompletableFuture<Void> reload(PreparationBarrier preparationBarrier, ResourceManager resourceManager, ProfilerFiller profilerFiller, ProfilerFiller profilerFiller2, Executor executor, Executor executor2) {

        return preparationBarrier.wait(Unit.INSTANCE).thenRunAsync(() ->{
            profilerFiller2.startTick();
            profilerFiller2.push("listener");
            scanFile(resourceManager);
            profilerFiller2.pop();
            profilerFiller2.endTick();
        },executor2);
    }

    private static void scanFile(ResourceManager resourceManager){
        Optional<Resource> resource = resourceManager.getResource(ResourceLocation.fromNamespaceAndPath("neoforge","loot_modifiers/global_loot_modifiers.json"));
        if(resource.isEmpty()){
            return;
        }

        try {
            Reader reader = resource.get().openAsReader();
            JsonElement jsonElement = GsonHelper.parse(reader);

            GlobalLootModifier globalLootModifier = GlobalLootModifier.CODEC.parse(JsonOps.INSTANCE,jsonElement).getOrThrow();
            List<ResourceLocation> entries = globalLootModifier.entries();
            reader.close();
            for(ResourceLocation id : entries){
                Resource loot = resourceManager.getResourceOrThrow(id.withPath("loot_modifiers/"+id.getPath()+".json"));
                Reader lootReader = loot.openAsReader();
                JsonElement lootJson =  GsonHelper.parse(lootReader);
                IGlobalLootModifier globalLootModifier1 = IGlobalLootModifier.CODEC.parse(JsonOps.INSTANCE,lootJson).getOrThrow();
                GLOBAL_LOOT_MODIFIERS.add(globalLootModifier1);
                lootReader.close();
            }


        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ResourceLocation getFabricId() {
        return MuffinsMcAPI.modid("neoforge_global_loot_table");
    }
}
