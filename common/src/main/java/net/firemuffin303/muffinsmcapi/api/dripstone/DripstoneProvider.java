package net.firemuffin303.muffinsmcapi.api.dripstone;

import com.google.common.collect.Sets;
import net.firemuffin303.muffinsmcapi.common.ModRegistries;
import net.firemuffin303.muffinsmcapi.impl.dripstone.data.BlockFluidInfo;
import net.firemuffin303.muffinsmcapi.impl.dripstone.data.FluidChanceInfo;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public abstract class DripstoneProvider implements DataProvider {
    private final PackOutput.PathProvider blockFluidPath;
    private final PackOutput.PathProvider fluidChancePath;
    private final CompletableFuture<HolderLookup.Provider> registries;

    public DripstoneProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture){
        this.blockFluidPath = packOutput.createRegistryElementsPathProvider(ModRegistries.BLOCK_FLUID_INFO);
        this.fluidChancePath = packOutput.createRegistryElementsPathProvider(ModRegistries.FLUID_CHANCE_INFO);
        this.registries = completableFuture;
    }

    public abstract void generateDripstoneData(HolderLookup.Provider provider,BlockFluidOutput blockFluidOutput,FluidChanceOutput fluidChanceOutput);

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        final Set<ResourceLocation> blockFluidID = Sets.newHashSet();
        final Set<ResourceLocation> fluidChanceID = Sets.newHashSet();

        final List<CompletableFuture<?>> list = new ArrayList<>();
        return this.registries.thenCompose(provider -> {
            this.generateDripstoneData(provider,(id, blockFluidInfo) -> {
                Objects.requireNonNull(id);
                Objects.requireNonNull(blockFluidInfo);
                if(!blockFluidID.add(id)){
                    throw new IllegalStateException("Duplicate dripstone block fluid " + id);
                }else{
                    list.add(DataProvider.saveStable(cachedOutput,provider,BlockFluidInfo.CODEC,blockFluidInfo,DripstoneProvider.this.blockFluidPath.json(id)));
                }
            },
            (id, fluidChanceInfo) -> {
                Objects.requireNonNull(id);
                Objects.requireNonNull(fluidChanceInfo);
                if(!fluidChanceID.add(id)){
                    throw new IllegalStateException("Duplicate dripstone block fluid " + id);
                }else{
                    list.add(DataProvider.saveStable(cachedOutput,provider,FluidChanceInfo.CODEC,fluidChanceInfo,DripstoneProvider.this.fluidChancePath.json(id)));
                }
            });

            return CompletableFuture.allOf(list.toArray(CompletableFuture[]::new));
        });
    }

    @Override
    public String getName() {
        return "dripstone_provider";
    }

    public interface BlockFluidOutput{
        void add(ResourceLocation id, BlockFluidInfo blockFluidInfo);
    }

    public interface FluidChanceOutput{
        void add(ResourceLocation id, FluidChanceInfo fluidChanceInfo);
    }
}
