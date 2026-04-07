package net.firemuffin303.muffinsmcapi.client;

import com.mojang.logging.LogUtils;
import net.firemuffin303.muffinsmcapi.api.CustomEffectRegistry;
import net.firemuffin303.muffinsmcapi.api.ModIntegrationRegistry;
import net.firemuffin303.muffinsmcapi.client.renderer.CustomRaidDebugRenderer;
import net.firemuffin303.muffinsmcapi.impl.customEffect.CustomEffectRenderer;
import net.firemuffin303.muffinsmcapi.network.camera.CameraShakePacket;
import net.firemuffin303.muffinsmcapi.network.customRaid.CustomRaidDebugPacket;
import net.firemuffin303.muffinsmcapi.util.PlatformUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class MuffinMcAPIClient {
    public static final CustomRaidDebugRenderer CUSTOM_RAID_DEBUG_RENDERER = new CustomRaidDebugRenderer(Minecraft.getInstance());

    public static void init(){
        ModIntegrationRegistry.register("jei", PlatformUtil.isJEIInstalled());
        ModIntegrationRegistry.register("emi", PlatformUtil.isEMIInstalled());

        CustomEffectRegistry.init();

        PlatformUtil.registerClientPacket(CameraShakePacket.TYPE,CameraShakePacket.STREAM_CODEC);
        PlatformUtil.registerClientPacket(CustomRaidDebugPacket.TYPE,CustomRaidDebugPacket.STREAM_CODEC);



        CustomEffectRegistry.register(new CustomEffectRenderer() {
            @Override
            public boolean shouldRender(LocalPlayer player) {
                return true;
            }

            @Override
            public Component getName(LocalPlayer player) {
                return Component.literal("I shat myself");
            }

            @Override
            public Component getDetail(LocalPlayer player) {
                return Component.literal("AGAIN");
            }

            @Override
            public ResourceLocation backgroundTextureWide(LocalPlayer player) {
                return null;
            }

            @Override
            public ResourceLocation backgroundTextureShort(LocalPlayer player) {
                return null;
            }

            @Override
            public ResourceLocation backgroundTextureHUD(LocalPlayer player) {
                return null;
            }

            @Override
            public ResourceLocation iconTexture(LocalPlayer player) {
                return null;
            }
        });
    }
}
