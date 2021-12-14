package sciwhiz12.snowyweaponry.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;

public class PotionConeItem extends Item {
    public static final int DURATION_DIVISOR = 8;

    public PotionConeItem(Item.Properties builder) {
        super(builder);
    }

    @Override
    public ItemStack getDefaultInstance() {
        return PotionUtils.setPotion(super.getDefaultInstance(), Potions.REGENERATION);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity living) {
        Player player = living instanceof Player ? (Player) living : null;
        if (player instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer) player, stack);
        }

        if (!worldIn.isClientSide) {
            for (MobEffectInstance effect : PotionUtils.getMobEffects(stack)) {
                living.addEffect(
                    new MobEffectInstance(effect.getEffect(), Math.max(effect.getDuration() / DURATION_DIVISOR, 1),
                        effect.getAmplifier(), effect.isAmbient(), effect.isVisible())
                );
            }
        }

        if (player != null) {
            player.awardStat(Stats.ITEM_USED.get(this));
        }

        return super.finishUsingItem(stack, worldIn, living);
    }

    @Override
    public String getDescriptionId(ItemStack stack) {
        return PotionUtils.getPotion(stack).getName(this.getDescriptionId() + ".effect.");
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        PotionUtils.addPotionTooltip(stack, tooltip, 1.0F / DURATION_DIVISOR);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return super.isFoil(stack) || !PotionUtils.getMobEffects(stack).isEmpty();
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        if (!this.allowdedIn(group)) return;

        for (Potion potion : ForgeRegistries.POTIONS) {
            if (potion != Potions.EMPTY) {
                items.add(PotionUtils.setPotion(new ItemStack(this), potion));
            }
        }
    }
}
