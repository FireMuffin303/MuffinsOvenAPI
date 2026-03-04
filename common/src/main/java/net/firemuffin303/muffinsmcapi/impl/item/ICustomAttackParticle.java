package net.firemuffin303.muffinsmcapi.impl.item;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public interface ICustomAttackParticle {

    boolean sweepAttackParticle(Player player, Level level);

    boolean critAttackEffect(Player player, Level level);

    boolean strongAttackEffect(Level level, Vec3 pos, SoundSource source);

    boolean weakAttackEffect(Level level, Vec3 pos, SoundSource source);
}
