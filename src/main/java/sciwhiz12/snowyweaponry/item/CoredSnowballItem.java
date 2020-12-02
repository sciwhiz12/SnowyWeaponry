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

import java.util.function.Supplier;
import javax.annotation.Nullable;

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

    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);

        world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW,
            SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

        if (!world.isRemote) {
            CoredSnowballEntity entity = new CoredSnowballEntity(world, player);
            entity.setItem(stack);
            entity.func_234612_a_(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
            world.addEntity(entity);
        }

        player.addStat(Stats.ITEM_USED.get(this));
        if (!player.abilities.isCreativeMode) {
            stack.shrink(1);
        }

        return ActionResult.func_233538_a_(stack, world.isRemote());
    }
}
