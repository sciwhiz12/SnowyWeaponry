package sciwhiz12.snowyweaponry.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import sciwhiz12.snowyweaponry.entity.CoredSnowballEntity;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class CoredSnowballItem extends Item {
    private final int thrownDamage;
    private final int lootingLevel;
    @Nullable
    private final Supplier<EffectInstance> hitEffect;

    public CoredSnowballItem(Item.Properties builder, int thrownDamage, int lootingLevel,
        @Nullable Supplier<EffectInstance> hitEffect) {
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
    public EffectInstance getHitEffect() {
        return hitEffect != null ? hitEffect.get() : null;
    }

    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);

        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW,
            SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

        if (!world.isClientSide) {
            CoredSnowballEntity entity = new CoredSnowballEntity(world, player);
            entity.setItem(stack);
            entity.shootFromRotation(player, player.xRot, player.yRot, 0.0F, 1.5F, 1.0F);
            world.addFreshEntity(entity);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.abilities.instabuild) {
            stack.shrink(1);
        }

        return ActionResult.sidedSuccess(stack, world.isClientSide());
    }
}
