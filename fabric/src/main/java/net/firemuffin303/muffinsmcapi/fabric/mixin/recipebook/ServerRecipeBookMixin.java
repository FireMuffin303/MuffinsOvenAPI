package net.firemuffin303.muffinsmcapi.fabric.mixin.recipebook;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.firemuffin303.muffinsmcapi.fabric.network.ModRecipePacket;
import net.firemuffin303.muffinsmcapi.impl.recipebooks.OvenRecipeBookRegistry;
import net.minecraft.network.protocol.game.ClientboundRecipePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.RecipeBook;
import net.minecraft.stats.ServerRecipeBook;
import net.minecraft.world.inventory.RecipeBookType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ServerRecipeBook.class)
public abstract class ServerRecipeBookMixin extends RecipeBook {
    @Inject(method = {"sendRecipes"}, at = {@At("TAIL")})
    public void muffins$sendRecipes(ClientboundRecipePacket.State state, ServerPlayer serverPlayer, List<ResourceLocation> list, CallbackInfo ci){
        OvenRecipeBookRegistry.INSTANCE.getRecipeBook().keySet().forEach(id -> {
            RecipeBookType recipeBookType = RecipeBookType.valueOf(id);
            ServerPlayNetworking.send(serverPlayer, new ModRecipePacket(recipeBookType,
                    this.getBookSettings().isOpen(recipeBookType),
                    this.getBookSettings().isFiltering(recipeBookType)
            ));
        });


    }
}
