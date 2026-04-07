package net.firemuffin303.muffinsmcapi.client.renderer;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.debug.DebugRenderer;
import net.minecraft.core.BlockPos;

import java.util.Collection;

public class CustomRaidDebugRenderer implements DebugRenderer.SimpleDebugRenderer {
    private Collection<BlockPos> raidCenters = Lists.newArrayList();
    private  Minecraft minecraft;

    public CustomRaidDebugRenderer(Minecraft minecraft){
        this.minecraft = minecraft;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, double d, double e, double f) {
        BlockPos blockPos = this.getCamera().getBlockPosition();
        for(BlockPos center : raidCenters){
            if(blockPos.closerThan(blockPos,160.0)){
                highlightRaidCenter(poseStack,multiBufferSource,center);
            }
        }
    }

    private static void highlightRaidCenter(PoseStack poseStack, MultiBufferSource multiBufferSource, BlockPos blockPos) {
        DebugRenderer.renderFilledUnitCube(poseStack, multiBufferSource, blockPos, 1.0F, 0.0F, 0.0F, 0.15F);
        int i = -65536;
        renderTextOverBlock(poseStack, multiBufferSource, "Raid center", blockPos, -65536);
    }

    private static void renderTextOverBlock(PoseStack poseStack, MultiBufferSource multiBufferSource, String string, BlockPos blockPos, int i) {
        double d = (double)blockPos.getX() + 0.5;
        double e = (double)blockPos.getY() + 1.3;
        double f = (double)blockPos.getZ() + 0.5;
        DebugRenderer.renderFloatingText(poseStack, multiBufferSource, string, d, e, f, i, 0.04F, true, 0.0F, true);
    }

    public void setRaidCenters(Collection<BlockPos> collection) {
        this.raidCenters = collection;
    }

    private Camera getCamera() {
        return this.minecraft.gameRenderer.getMainCamera();
    }
}
