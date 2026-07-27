package net.firemuffin303.muffinsmcapi.common.data;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.JsonOps;
import net.firemuffin303.muffinsmcapi.api.block.dripstone.DripstoneFluidRegistry;
import net.firemuffin303.muffinsmcapi.common.ModRegistries;
import net.firemuffin303.muffinsmcapi.impl.dripstone.data.BlockFluidInfo;
import net.firemuffin303.muffinsmcapi.impl.dripstone.data.FluidChanceInfo;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class DripstoneDataManager extends SimplePreparableReloadListener<Map<String,Map<ResourceLocation,JsonElement>>> {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static Map<ResourceLocation, BlockFluidInfo> BLOCK_FLUID_INFO_MAP = new HashMap<>();
    private static Map<ResourceLocation, FluidChanceInfo> FLUID_CHANCE_INFO_MAP = new HashMap<>();
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    private final HolderLookup.Provider registries;

    public DripstoneDataManager(HolderLookup.Provider provider){
        this.registries = provider;
    }


    @Override
    protected Map<String, Map<ResourceLocation, JsonElement>> prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        Map<String,Map<ResourceLocation,JsonElement>> mapMap = new HashMap<>();
        Map<ResourceLocation,JsonElement> blockFluid = new HashMap<>();
        Map<ResourceLocation,JsonElement> fluidChance = new HashMap<>();
        SimpleJsonResourceReloadListener.scanDirectory(resourceManager,ModRegistries.BLOCK_FLUID_INFO.location().getPath(),GSON,blockFluid);
        SimpleJsonResourceReloadListener.scanDirectory(resourceManager,ModRegistries.FLUID_CHANCE_INFO.location().getPath(),GSON,fluidChance);
        mapMap.put("block_fluid",blockFluid);
        mapMap.put("fluid_chance",fluidChance);
        return mapMap;
    }

    @Override
    protected void apply(Map<String, Map<ResourceLocation, JsonElement>> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        RegistryOps<JsonElement> registryOps = this.registries.createSerializationContext(JsonOps.INSTANCE);
        ImmutableMap.Builder<ResourceLocation, BlockFluidInfo> builder = ImmutableMap.builder();
        ImmutableMap.Builder<ResourceLocation, FluidChanceInfo> fluidChanceInfoBuilder = ImmutableMap.builder();



        object.get("block_fluid").forEach((resourceLocation, jsonElement) -> {
            try{
                BlockFluidInfo blockFluidInfo = BlockFluidInfo.CODEC.parse(registryOps,jsonElement).getOrThrow(JsonParseException::new);
                builder.put(resourceLocation,blockFluidInfo);
            }catch(Exception e){
                LOGGER.error("Parsing error loading dripstone block fluid {}: {}",resourceLocation,e.getMessage());
            }
        });

        object.get("fluid_chance").forEach((resourceLocation, jsonElement) -> {
            try{
                FluidChanceInfo fluidChanceInfo = FluidChanceInfo.CODEC.parse(registryOps,jsonElement).getOrThrow(JsonParseException::new);
                fluidChanceInfoBuilder.put(resourceLocation,fluidChanceInfo);
            }catch (Exception e){
                LOGGER.error("Parsing error loading dripstone fluid chance {}: {}",resourceLocation,e.getMessage());
            }
        });

        BLOCK_FLUID_INFO_MAP = builder.build();
        FLUID_CHANCE_INFO_MAP = fluidChanceInfoBuilder.build();

        BLOCK_FLUID_INFO_MAP.forEach((resourceLocation, blockFluidInfo) -> {
            DripstoneFluidRegistry.FLUID.put(blockFluidInfo.blockState(), blockFluidInfo.fluid());
        });

        FLUID_CHANCE_INFO_MAP.forEach((resourceLocation, fluidChanceInfo) -> {
            DripstoneFluidRegistry.FLUID_DRIPSTONE_CHANCE.put(fluidChanceInfo.fluid(), fluidChanceInfo.chance());
        });

        LOGGER.info("Loaded {} dripstone block fluid info",BLOCK_FLUID_INFO_MAP.size());
        LOGGER.info("Loaded {} dripstone fluid chance info",FLUID_CHANCE_INFO_MAP.size());
    }
}