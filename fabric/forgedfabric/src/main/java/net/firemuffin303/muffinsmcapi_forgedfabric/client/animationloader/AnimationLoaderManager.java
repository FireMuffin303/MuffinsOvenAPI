package net.firemuffin303.muffinsmcapi_forgedfabric.client.animationloader;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.logging.LogUtils;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.firemuffin303.muffinsmcapi_forgedfabric.ForgedFabricAPI;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.slf4j.Logger;

import java.util.Map;

public class AnimationLoaderManager extends SimpleJsonResourceReloadListener implements IdentifiableResourceReloadListener {
    private static final Logger LOGGER = LogUtils.getLogger();

    public AnimationLoaderManager() {
        super(new Gson(), "neoforge/animations/entity");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {

    }

    @Override
    public ResourceLocation getFabricId() {
        return ForgedFabricAPI.modid("neoforge_animation_loader");
    }
}
