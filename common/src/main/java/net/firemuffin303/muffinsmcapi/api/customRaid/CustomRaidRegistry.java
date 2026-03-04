package net.firemuffin303.muffinsmcapi.api.customRaid;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.firemuffin303.muffinsmcapi.common.data.RaidDataManager;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.commands.arguments.coordinates.Vec3Argument;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public class CustomRaidRegistry {


    private static final SuggestionProvider<CommandSourceStack> SUGGEST_ADVANCEMENTS = (commandContext, suggestionsBuilder) -> {
        return SharedSuggestionProvider.suggestResource(RaidDataManager.getRaidID(), suggestionsBuilder);
    };

    public static void registerCommand(CommandDispatcher<CommandSourceStack> commandDispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection commandSelection) {
        commandDispatcher.register(
                Commands.literal("customRaid")
                        .requires(source -> source.hasPermission(4))
                        .then(Commands.argument("centerPos",Vec3Argument.vec3())
                                .then(Commands.argument("raidID", ResourceLocationArgument.id()).suggests(SUGGEST_ADVANCEMENTS)
                                        .then(Commands.argument("level", IntegerArgumentType.integer(1))
                                                .executes(context -> {
                                                    Vec3 centerPos = Vec3Argument.getVec3(context,"centerPos");
                                                    ResourceLocation raidID = ResourceLocationArgument.getId(context,"raidID");
                                                    context.getSource().sendSuccess(() -> Component.literal("Raid %s created at %s".formatted(raidID.toString(),centerPos.toString())),false);
                                                    return 1;
                                                })
                                        )
                                )
                        )

        );
    }
}
