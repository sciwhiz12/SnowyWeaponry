package sciwhiz12.snowyweaponry.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import sciwhiz12.snowyweaponry.entity.ExplosiveSnowballEntity;

public class ExplosiveSnowballItem extends Item {
    public ExplosiveSnowballItem(Item.Properties builder) {
        super(builder);
    }

    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);

        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW,
            SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

        if (!world.isClientSide) {
            ExplosiveSnowballEntity entity = new ExplosiveSnowballEntity(world, player);
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
