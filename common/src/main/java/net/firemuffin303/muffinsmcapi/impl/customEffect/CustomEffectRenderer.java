package net.firemuffin303.muffinsmcapi.impl.customEffect;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

/**
 * Interface to create custom effect renderer, display similar to potion effect toast on player's inventory screen.
 *
 * **/
public interface CustomEffectRenderer {

    /**
     * @param player player for getting data.
     * @return should render this custom effect renderer.
     * **/
    boolean shouldRender(LocalPlayer player);

    /**
     * @param player player for getting data.
     * @return Custom Effect's Display name.
     * **/
    Component getName(LocalPlayer player);

    /**
     * @param player player for getting data.
     * @return Detail of Custom Effect. (e.g. timer)
     * **/
    Component getDetail(LocalPlayer player);

    /**
     * @return Custom Effect's icon.
     * @param player player for getting data.
     * **/
    ResourceLocation iconTexture(LocalPlayer player);

    /**
     * @param player player for getting data.
     * @return color of custom effect's toast border.
     * **/
    default int color(LocalPlayer player){
        return 0xFFFFFF;
    }
}
