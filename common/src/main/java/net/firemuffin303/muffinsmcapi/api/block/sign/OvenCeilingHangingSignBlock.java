package net.firemuffin303.muffinsmcapi.api.block.sign;

import net.firemuffin303.muffinsmcapi.api.BlockEntityTypeUtil;
import net.firemuffin303.muffinsmcapi.impl.block.OvenSign;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class OvenCeilingHangingSignBlock extends CeilingHangingSignBlock implements OvenSign {
    final String signID;

    public OvenCeilingHangingSignBlock(Properties arg,String signID) {
        super(arg,WoodType.OAK);
        BlockEntityTypeUtil.addBlockEntityType(BlockEntityType.HANGING_SIGN,this);
        this.signID = signID;
    }

    @Override
    public String getSignID() {
        return this.signID;
    }
}
