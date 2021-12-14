package sciwhiz12.snowyweaponry.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.stats.Stats;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
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
    public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity living) {
        PlayerEntity player = living instanceof PlayerEntity ? (PlayerEntity) living : null;
        if (player instanceof ServerPlayerEntity) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayerEntity) player, stack);
        }

        if (!worldIn.isClientSide) {
            for (EffectInstance effect : PotionUtils.getMobEffects(stack)) {
                living.addEffect(
                    new EffectInstance(effect.getEffect(), Math.max(effect.getDuration() / DURATION_DIVISOR, 1),
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
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        PotionUtils.addPotionTooltip(stack, tooltip, 1.0F / DURATION_DIVISOR);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return super.isFoil(stack) || !PotionUtils.getMobEffects(stack).isEmpty();
    }

    @Override
    public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
        if (!this.allowdedIn(group)) return;

        for (Potion potion : ForgeRegistries.POTION_TYPES) {
            if (potion != Potions.EMPTY) {
                items.add(PotionUtils.setPotion(new ItemStack(this), potion));
            }
        }
    }
}
