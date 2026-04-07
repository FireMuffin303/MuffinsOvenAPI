package net.firemuffin303.muffinsmcapi.api.customRaid;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.logging.LogUtils;
import net.firemuffin303.muffinsmcapi.common.data.RaidDataManager;
import net.firemuffin303.muffinsmcapi.impl.customRaid.CustomRaid;
import net.firemuffin303.muffinsmcapi.impl.customRaid.CustomRaidManager;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.commands.arguments.coordinates.Vec3Argument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.phys.Vec3;

public class CustomRaidRegistry {


    private static final SuggestionProvider<CommandSourceStack> SUGGEST_ADVANCEMENTS = (commandContext, suggestionsBuilder) -> {
        return SharedSuggestionProvider.suggestResource(RaidDataManager.getRaidID(), suggestionsBuilder);
    };

    public static void registerCommand(CommandDispatcher<CommandSourceStack> commandDispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection commandSelection) {
        commandDispatcher.register(
                Commands.literal("customRaid").requires(source -> source.hasPermission(4))
                        .then(Commands.literal("start")
                                .then(Commands.argument("centerPos", BlockPosArgument.blockPos())
                                        .then(Commands.argument("raidID", ResourceLocationArgument.id()).suggests(SUGGEST_ADVANCEMENTS)
                                                .then(Commands.argument("level", IntegerArgumentType.integer(1))
                                                        .executes(context -> {
                                                            CommandSourceStack commandSourceStack = context.getSource();
                                                            BlockPos centerPos = BlockPosArgument.getBlockPos(context,"centerPos");
                                                            ResourceLocation raidID = ResourceLocationArgument.getId(context,"raidID");
                                                            ServerPlayer serverPlayer = commandSourceStack.getPlayerOrException();
                                                            ServerLevel  serverLevel = serverPlayer.serverLevel();

                                                            CustomRaidManager customRaidManager = serverLevel.getDataStorage().computeIfAbsent(CustomRaidManager.factory(serverLevel),"custom_raid");
                                                            if(customRaidManager.isRaided(centerPos)){
                                                                commandSourceStack.sendFailure(Component.literal("Custom Raid already started close by"));
                                                                return -1;
                                                            }
                                                            CustomRaid customRaid = customRaidManager.createOrExtendRaid(raidID,serverPlayer,centerPos);
                                                            if(customRaid != null){
                                                                customRaid.setRaidLevel(IntegerArgumentType.getInteger(context,"level"));
                                                                customRaidManager.setDirty();
                                                                context.getSource().sendSuccess(() -> Component.literal("Raid %s created at %s".formatted(raidID.toString(),centerPos.toString())),false);
                                                                return 1;
                                                            }
                                                            commandSourceStack.sendFailure(Component.literal("Failed to create a raid."));
                                                            return -1;
                                                        })
                                                )
                                        )
                                )
                        )
                        .then(Commands.literal("stop")
                                .executes(commandContext -> {
                                    CommandSourceStack commandSourceStack = commandContext.getSource();
                                    ServerPlayer serverPlayer = commandSourceStack.getPlayerOrException();
                                    BlockPos blockPos = serverPlayer.blockPosition();
                                    ServerLevel serverLevel = serverPlayer.serverLevel();
                                    CustomRaidManager customRaidManager = serverLevel.getDataStorage().computeIfAbsent(CustomRaidManager.factory(serverLevel),"custom_raid");

                                    CustomRaid raid = customRaidManager.getNearbyRaid(blockPos,9216);
                                    if (raid != null) {
                                        raid.stop();
                                        commandSourceStack.sendSuccess(() -> {
                                            return Component.literal("Stopped raid %s".formatted(raid.getRaidID()));
                                        }, false);
                                        return 1;
                                    } else {
                                        commandSourceStack.sendFailure(Component.literal("No raid here"));
                                        return -1;
                                    }
                                })
                        )
        );
    }
}
