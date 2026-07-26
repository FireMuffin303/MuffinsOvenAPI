package net.firemuffin303.muffinsmcapi.api;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import net.firemuffin303.muffinsmcapi.impl.camera.ICameraShake;
import net.firemuffin303.muffinsmcapi.network.CameraShakePacket;
import net.firemuffin303.muffinsmcapi.util.PlatformUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class CameraAPI {

    public static void clientCameraShake(float intensity,float reductionRate){
        ICameraShake cameraShake = ((ICameraShake) Minecraft.getInstance().gameRenderer.getMainCamera());
        cameraShake.muffinsmcapi$setShakeIntensity(intensity);
        cameraShake.muffinsmcapi$setShakeReduction(reductionRate);
    }

    public static void serverCameraShake(ServerPlayer player,float intensity,float reductionRate){
        PlatformUtil.sendServerPacket(player,new CameraShakePacket(intensity,reductionRate));
    }

    public static void registerCommand(CommandDispatcher<CommandSourceStack> commandDispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection commandSelection){
        commandDispatcher.register(
                Commands.literal("cameraShake")
                        .requires(source -> source.hasPermission(4))
                        .then(Commands.argument("player", EntityArgument.player())
                                .then(Commands.argument("intensity", FloatArgumentType.floatArg())
                                        .executes(context -> {
                                            ServerPlayer serverPlayer = EntityArgument.getPlayer(context,"player");
                                            CameraAPI.serverCameraShake(serverPlayer,FloatArgumentType.getFloat(context,"intensity"),0.1f);
                                            context.getSource().sendSuccess(() -> Component.literal("Applied camera shake for %s".formatted(serverPlayer.getName().getString())),false);
                                            return 1;
                                        })
                                        .then(Commands.argument("reductionRate",FloatArgumentType.floatArg())
                                                .executes(context -> {
                                                    ServerPlayer serverPlayer = EntityArgument.getPlayer(context,"player");
                                                    CameraAPI.serverCameraShake(serverPlayer,FloatArgumentType.getFloat(context,"intensity"),FloatArgumentType.getFloat(context,"reductionRate"));
                                                    context.getSource().sendSuccess(() -> Component.literal("Applied camera shake for %s".formatted(serverPlayer.getName().getString())),false);
                                                    return 1;
                                                })
                                        )
                                )
                        )
        );
    }
}