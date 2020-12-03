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

import java.util.List;
import javax.annotation.Nullable;

public class PotionConeItem extends Item {
    public static final int DURATION_DIVISOR = 8;

    public PotionConeItem(Item.Properties builder) {
        super(builder);
    }

    @Override
    public ItemStack getDefaultInstance() {
        return PotionUtils.addPotionToItemStack(super.getDefaultInstance(), Potions.REGENERATION);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity living) {
        PlayerEntity player = living instanceof PlayerEntity ? (PlayerEntity) living : null;
        if (player instanceof ServerPlayerEntity) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayerEntity) player, stack);
        }

        if (!worldIn.isRemote) {
            for (EffectInstance effect : PotionUtils.getEffectsFromStack(stack)) {
                living.addPotionEffect(
                    new EffectInstance(effect.getPotion(), Math.max(effect.getDuration() / DURATION_DIVISOR, 1),
                        effect.getAmplifier(), effect.isAmbient(), effect.doesShowParticles())
                );
            }
        }

        if (player != null) {
            player.addStat(Stats.ITEM_USED.get(this));
        }

        return super.onItemUseFinish(stack, worldIn, living);
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return PotionUtils.getPotionFromItem(stack).getNamePrefixed(this.getTranslationKey() + ".effect.");
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        PotionUtils.addPotionTooltip(stack, tooltip, 1.0F / DURATION_DIVISOR);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return super.hasEffect(stack) || !PotionUtils.getEffectsFromStack(stack).isEmpty();
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (!this.isInGroup(group)) return;

        for (Potion potion : ForgeRegistries.POTION_TYPES) {
            if (potion != Potions.EMPTY) {
                items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), potion));
            }
        }
    }
}
