package net.firemuffin303.muffinsmcapi.fabric.mixin.multipart;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.firemuffin303.muffinsmcapi.fabric.common.multipart.PartEntity;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.world.entity.boss.EnderDragonPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ChunkMap.class)
public abstract class ChunkMapMixin {

    @Definition(id = "EnderDragonPart", type = EnderDragonPart.class)
    @Expression("? instanceof EnderDragonPart")
    @WrapOperation(method = "addEntity", at = @At("MIXINEXTRAS:EXPRESSION"))
    public boolean muffins$modifyPartCheck(Object object, Operation<Boolean> original){
        if( object instanceof PartEntity<?>){
            return false;
        }

        return original.call(object);
    }
}
