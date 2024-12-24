package dev.sciwhiz12.snowyweaponry.item;

import dev.sciwhiz12.snowyweaponry.entity.CoredSnowball;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.function.Supplier;

public class CoredSnowballItem extends Item implements ProjectileItem {
    private final int thrownDamage;
    private final int lootingLevel;
    @Nullable
    private final Supplier<MobEffectInstance> hitEffect;

    public CoredSnowballItem(Item.Properties properties, int thrownDamage, int lootingLevel,
                             @Nullable Supplier<MobEffectInstance> hitEffect) {
        super(properties);
        this.thrownDamage = thrownDamage;
        this.lootingLevel = lootingLevel;
        this.hitEffect = hitEffect;
    }

    public CoredSnowballItem(Item.Properties properties, int thrownDamage, int lootingLevel) {
        this(properties, thrownDamage, lootingLevel, null);
    }

    public int getDamage() {
        return thrownDamage;
    }

    public int getLootingLevel() {
        return lootingLevel;
    }

    @Nullable
    public MobEffectInstance getHitEffect() {
        return hitEffect != null ? hitEffect.get() : null;
    }

    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW,
                SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));

        if (level instanceof ServerLevel serverlevel) {
            Projectile.spawnProjectileFromRotation(CoredSnowball::new, serverlevel, stack, player, 0.0F, 1.5F, 1.0F);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        stack.consume(1, player);
        return InteractionResult.SUCCESS;
    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        return new CoredSnowball(level, pos.x(), pos.y(), pos.z(), stack);
    }
}
