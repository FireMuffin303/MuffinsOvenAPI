package net.firemuffin303.muffinsmcapi.common.data;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.JsonOps;
import net.firemuffin303.muffinsmcapi.common.ModRegistries;
import net.firemuffin303.muffinsmcapi.impl.customRaid.common.CustomRaidData;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.Collection;
import java.util.Map;

public class RaidDataManager extends SimpleJsonResourceReloadListener {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static Map<ResourceLocation, CustomRaidData> CUSTOM_RAID_MAP = Map.of();
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    private final HolderLookup.Provider registries;

    public RaidDataManager(HolderLookup.Provider provider) {
        super(GSON, Registries.elementsDirPath(ModRegistries.RAID_DATA));
        this.registries = provider;

    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        RegistryOps<JsonElement> registryOps = this.registries.createSerializationContext(JsonOps.INSTANCE);
        ImmutableMap.Builder<ResourceLocation, CustomRaidData> builder = ImmutableMap.builder();
        object.forEach((resourceLocation, jsonElement) -> {
            try{
                CustomRaidData customRaidData = CustomRaidData.CODEC.parse(registryOps,jsonElement).getOrThrow(JsonParseException::new);
                builder.put(resourceLocation,customRaidData);
            }catch (Exception e){
                LOGGER.error("Parsing error loading custom raid data {}: {}",resourceLocation,e.getMessage());
            }
        });
        CUSTOM_RAID_MAP = builder.build();
        LOGGER.info("Loaded {} raid data",CUSTOM_RAID_MAP.size());
    }

    @Nullable
    public CustomRaidData get(ResourceLocation resourceLocation){
        return CUSTOM_RAID_MAP.get(resourceLocation);
    }

    public Collection<CustomRaidData> getAllCustomRaids(){
        return CUSTOM_RAID_MAP.values();
    }

    public static Collection<ResourceLocation> getRaidID(){
        return CUSTOM_RAID_MAP.keySet();
    }


}
