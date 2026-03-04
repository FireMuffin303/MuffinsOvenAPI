package net.firemuffin303.muffinsmcapi.api.customRaid;

import com.google.common.collect.Sets;
import net.firemuffin303.muffinsmcapi.common.ModRegistries;
import net.firemuffin303.muffinsmcapi.impl.customRaid.common.CustomRaidData;
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

public abstract class APIRaidDataProvider implements DataProvider {
    private final PackOutput.PathProvider pathProvider;
    private final CompletableFuture<HolderLookup.Provider> registries;

    public APIRaidDataProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture){
        this.pathProvider = packOutput.createRegistryElementsPathProvider(ModRegistries.RAID_DATA);
        this.registries = completableFuture;
    }

    public abstract void generateRaidData(HolderLookup.Provider provider, RaidDataOutput raidDataBuilder);

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {

        return this.registries.thenCompose(provider -> {
            final Set<ResourceLocation> set = Sets.newHashSet();
            final List<CompletableFuture<?>> list = new ArrayList<>();
            this.generateRaidData(provider, (id, customRaidData) -> {
                Objects.requireNonNull(id);
                Objects.requireNonNull(customRaidData);
                if(!set.add(id)){
                    throw new IllegalStateException("Duplicate raid data" + id);
                } else {
                    list.add(DataProvider.saveStable(cachedOutput,provider,CustomRaidData.CODEC,customRaidData,APIRaidDataProvider.this.pathProvider.json(id)));
                }
            });

            return CompletableFuture.allOf(list.toArray(CompletableFuture[]::new));
        });
    }

    @Override
    public String getName() {
        return "raid_provider";
    }

    public interface RaidDataOutput {
        void add(ResourceLocation id, CustomRaidData customRaidData);
    }
}
