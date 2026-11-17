package net.firemuffin303.muffinsmcapi.common.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;

public class WallClimbNodeEvaluator extends WalkNodeEvaluator {

    @Override
    public Node getStart() {
        //I have to make it able to start on walls.
        return super.getStart();
    }

    @Override
    public int getNeighbors(Node[] nodes, Node node) {
        int i = super.getNeighbors(nodes, node);
        int stepHeight = 0;
        PathType pathType = this.getCachedPathType(node.x, node.y + 1, node.z);
        PathType pathType2 = this.getCachedPathType(node.x, node.y, node.z);
        if(this.mob.getPathfindingMalus(pathType) >= 0.0f && pathType2 != PathType.STICKY_HONEY){
            stepHeight = Mth.floor(Math.max(1.0F, this.mob.maxUpStep()));
        }

        return super.getNeighbors(nodes, node);
    }
}
