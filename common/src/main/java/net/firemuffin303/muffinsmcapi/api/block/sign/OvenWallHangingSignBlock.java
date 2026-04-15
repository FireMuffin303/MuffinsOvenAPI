package net.firemuffin303.muffinsmcapi.api.block.sign;

import net.firemuffin303.muffinsmcapi.api.BlockEntityTypeUtil;
import net.firemuffin303.muffinsmcapi.impl.block.OvenSign;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class OvenWallHangingSignBlock extends WallHangingSignBlock implements OvenSign {
    final String signID;
    public OvenWallHangingSignBlock(Properties arg,String signID) {
        super(arg, WoodType.OAK);
        BlockEntityTypeUtil.addBlockEntityType(BlockEntityType.HANGING_SIGN,this);
        this.signID = signID;
    }

    @Override
    public String getSignID() {
        return this.signID;
    }
}
