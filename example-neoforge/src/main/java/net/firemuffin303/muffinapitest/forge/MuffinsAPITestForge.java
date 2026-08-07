package net.firemuffin303.muffinapitest.forge;

import com.mojang.logging.LogUtils;
import net.firemuffin303.muffinapitest.MuffinsAPITest;
import net.firemuffin303.muffinapitest.common.registry.ModMobEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.stream.Collectors;

@Mod(MuffinsAPITest.MOD_ID)
public class MuffinsAPITestForge {

    public MuffinsAPITestForge(IEventBus eventBus){
        MuffinsAPITest.init();

        NeoForge.EVENT_BUS.addListener(this::registerUseBlock);
    }

    public void registerUseBlock(PlayerInteractEvent.RightClickItem event){
        Player player = event.getEntity();
        ItemStack itemStack = event.getItemStack();
        if(itemStack.is(Items.STICK)){
            if(player.level().isClientSide){
                LogUtils.getLogger().info("{}",player.hasEffect(ModMobEffects.JARONA));

            }
        }
    }
}
