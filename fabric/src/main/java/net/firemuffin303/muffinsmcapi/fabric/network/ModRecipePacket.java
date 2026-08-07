package net.firemuffin303.muffinsmcapi.fabric.network;

import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.inventory.RecipeBookType;

public record ModRecipePacket(RecipeBookType bookType, boolean open, boolean filtering) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<ModRecipePacket> TYPE = new Type<>(MuffinsMcAPI.modid("mod_recipe_packet"));
    public static final StreamCodec<FriendlyByteBuf, ModRecipePacket> STREAM_CODEC = CustomPacketPayload.codec(ModRecipePacket::write,ModRecipePacket::new);


    private ModRecipePacket(FriendlyByteBuf friendlyByteBuf){
        this(friendlyByteBuf.readEnum(RecipeBookType.class),friendlyByteBuf.readBoolean(),friendlyByteBuf.readBoolean());
    }

    private void write(FriendlyByteBuf friendlyByteBuf){
        friendlyByteBuf.writeEnum(this.bookType);
        friendlyByteBuf.writeBoolean(this.open);
        friendlyByteBuf.writeBoolean(this.filtering);
    }

    public void handle(){
        Minecraft.getInstance().execute(() -> {
            ClientRecipeBook recipeBook = Minecraft.getInstance().player.getRecipeBook();
            recipeBook.setOpen(this.bookType, this.open);
            recipeBook.setFiltering(this.bookType, this.filtering);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
