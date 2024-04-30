package tk.sciwhiz12.snowyweaponry.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

public class PotionConeItem extends Item {
    public static final int DURATION_DIVISOR = 8;

    public PotionConeItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack itemstack = super.getDefaultInstance();
        itemstack.set(DataComponents.POTION_CONTENTS, new PotionContents(Potions.WATER));
        return itemstack;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        @Nullable Player player = user instanceof Player ? (Player) user : null;
        if (player instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer) player, stack);
        }

        if (!level.isClientSide) {
            PotionContents contents = stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
            if (contents.potion().isPresent()) {
                for (MobEffectInstance effect : contents.potion().get().value().getEffects()) {
                    user.addEffect(
                            new MobEffectInstance(
                                    effect.getEffect(),
                                    Math.max(effect.mapDuration(duration -> duration / DURATION_DIVISOR), 1),
                                    effect.getAmplifier(),
                                    effect.isAmbient(),
                                    effect.isVisible()
                            )
                    );
                }
            }

            for (MobEffectInstance effect : contents.customEffects()) {
                user.addEffect(effect);
            }
        }

        if (player != null) {
            player.awardStat(Stats.ITEM_USED.get(this));
        }

        user.gameEvent(GameEvent.DRINK);
        return super.finishUsingItem(stack, level, user);
    }

    @Override
    public String getDescriptionId(ItemStack stack) {
        return Potion.getName(stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY).potion(),
                this.getDescriptionId() + ".effect.");
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        @Nullable PotionContents potioncontents = stack.get(DataComponents.POTION_CONTENTS);
        if (potioncontents != null) {
            potioncontents.addPotionTooltip(tooltip::add, 1.0F / DURATION_DIVISOR, context.tickRate());
        }
    }
}
