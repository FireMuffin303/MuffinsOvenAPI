package net.firemuffin303.muffinsmcapi.api.block.sign;

import net.firemuffin303.muffinsmcapi.api.BlockEntityTypeUtil;
import net.firemuffin303.muffinsmcapi.impl.block.OvenSign;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class OvenWallSignBlock extends WallSignBlock implements OvenSign {
    final String signID;

    public OvenWallSignBlock(Properties arg, String signID) {
        super(WoodType.OAK,arg);
        BlockEntityTypeUtil.addBlockEntityType(BlockEntityType.SIGN,this);
        this.signID = signID;
    }

    @Override
    public String getSignID() {
        return this.signID;
    }
}
