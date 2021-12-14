package sciwhiz12.snowyweaponry.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import sciwhiz12.snowyweaponry.entity.CoredSnowballEntity;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class CoredSnowballItem extends Item {
    private final int thrownDamage;
    private final int lootingLevel;
    @Nullable
    private final Supplier<MobEffectInstance> hitEffect;

    public CoredSnowballItem(Item.Properties builder, int thrownDamage, int lootingLevel,
                             @Nullable Supplier<MobEffectInstance> hitEffect) {
        super(builder);
        this.thrownDamage = thrownDamage;
        this.lootingLevel = lootingLevel;
        this.hitEffect = hitEffect;
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

    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW,
            SoundSource.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

        if (!world.isClientSide) {
            CoredSnowballEntity entity = new CoredSnowballEntity(world, player);
            entity.setItem(stack);
            entity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            world.addFreshEntity(entity);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(stack, world.isClientSide());
    }
}
