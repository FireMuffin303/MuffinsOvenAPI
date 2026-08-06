package net.firemuffin303.muffinsmcapi.fabric;

import com.chocohead.mm.api.ClassTinkerers;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.fabricmc.api.ModInitializer;
import net.firemuffin303.muffinsmcapi.api.BlockEntityTypeUtil;
import net.firemuffin303.muffinsmcapi.api.BoatRegistry;
import net.firemuffin303.muffinsmcapi.api.CameraAPI;
import net.firemuffin303.muffinsmcapi.common.data.DripstoneDataManager;
import net.firemuffin303.muffinsmcapi.fabric.api.CustomRegistryHelper;
import net.firemuffin303.muffinsmcapi.fabric.api.FabricOvenRegistry;
import net.firemuffin303.muffinsmcapi.fabric.mixin.recipebook.RecipeBookCategoriesAccessor;
import net.firemuffin303.muffinsmcapi.fabric.mixin.recipebook.RecipeBookSettingsAccessor;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.firemuffin303.muffinsmcapi.impl.recipebooks.OvenRecipeBookRegistry;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.inventory.RecipeBookType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class MuffinsmcapiFabric implements ModInitializer {

    private static final Registry<OvenBoatVariant> registryOvenBoat = FabricOvenRegistry.OVEN_BOAT_VARIANT_REGISTRY;
    public static final CustomRegistryHelper INSTANCE = new CustomRegistryHelper();
    private static final Registry<OvenBoatVariant> OVEN_BOAT_VARIANT_REGISTRY = INSTANCE.register(BoatRegistry.OVEN_BOAT_VARIANT,registryOvenBoat);


    @Override
    public void onInitialize() {
        MuffinsMcAPI.init();

        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(MuffinsMcAPI.modid("dripstone_data"), new Function<HolderLookup.Provider, IdentifiableResourceReloadListener>() {
            @Override
            public IdentifiableResourceReloadListener apply(HolderLookup.Provider provider) {
                final DripstoneDataManager dripstoneDataManager = new DripstoneDataManager(provider);
                return new IdentifiableResourceReloadListener() {
                    @Override
                    public ResourceLocation getFabricId() {
                        return MuffinsMcAPI.modid("dripstone_data");
                    }

                    @Override
                    public CompletableFuture<Void> reload(PreparationBarrier preparationBarrier, ResourceManager resourceManager, ProfilerFiller profilerFiller, ProfilerFiller profilerFiller2, Executor executor, Executor executor2) {
                        return dripstoneDataManager.reload(preparationBarrier, resourceManager, profilerFiller, profilerFiller2, executor, executor2);
                    }
                };
            }
        });

        CommandRegistrationCallback.EVENT.register(new CommandRegistrationCallback() {
            @Override
            public void register(CommandDispatcher<CommandSourceStack> commandDispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection commandSelection) {
                CameraAPI.registerCommand(commandDispatcher, commandBuildContext, commandSelection);
            }
        });

        Map<RecipeBookCategories, List<RecipeBookCategories>> re = OvenRecipeBookRegistry.INSTANCE.getMODDED_AGGREGATE_CATEGORY().entrySet().stream()
                .collect(
                        Collectors.toMap(
                                entry -> ClassTinkerers.getEnum(RecipeBookCategories.class,OvenRecipeBookRegistry.convertID(entry.getKey())),
                                entry -> entry.getValue().stream().map(resourceLocation -> ClassTinkerers.getEnum(RecipeBookCategories.class,OvenRecipeBookRegistry.convertID(resourceLocation))).toList()
                        )
                );

        RecipeBookCategoriesAccessor.setAggregateCategory(
                Stream.concat(RecipeBookCategories.AGGREGATE_CATEGORIES.entrySet().stream(),
                        re.entrySet().stream()
                ).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue)));

    }
}
