package net.firemuffin303.muffinsmcapi.api.block.sign;

import net.firemuffin303.muffinsmcapi.api.BlockEntityTypeUtil;
import net.firemuffin303.muffinsmcapi.impl.block.OvenSign;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class OvenStandSignBlock extends StandingSignBlock implements OvenSign {
    final String signID;
    public OvenStandSignBlock(Properties arg,String signID) {
        super(WoodType.OAK,arg);
        BlockEntityTypeUtil.addBlockEntityType(BlockEntityType.SIGN,this);
        this.signID = signID;
    }

    @Override
    public String getSignID() {
        return this.signID;
    }
}
