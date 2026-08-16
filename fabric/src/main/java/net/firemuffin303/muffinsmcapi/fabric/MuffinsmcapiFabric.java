package net.firemuffin303.muffinsmcapi.fabric;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.fabricmc.api.ModInitializer;
import net.firemuffin303.muffinsmcapi.api.CameraAPI;
import net.firemuffin303.muffinsmcapi.common.data.DripstoneDataManager;
import net.firemuffin303.muffinsmcapi.fabric.api.CustomRegistryHelper;
import net.firemuffin303.muffinsmcapi.fabric.network.ModRecipePacket;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;

public final class MuffinsmcapiFabric implements ModInitializer {
    public static final CustomRegistryHelper INSTANCE = new CustomRegistryHelper();

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

        PayloadTypeRegistry.playS2C().register(ModRecipePacket.TYPE,ModRecipePacket.STREAM_CODEC);
    }
}
