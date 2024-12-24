package dev.sciwhiz12.snowyweaponry.item;

import dev.sciwhiz12.snowyweaponry.Reference;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PotionConeItem extends Item {
    public static final int DURATION_DIVISOR = 8;

    public PotionConeItem(Item.Properties properties) {
        super(properties);
    }

    public static PotionContents modifyEffects(PotionContents original) {
        // PotionContents applies the *full* duration of the potion on consume, without a way to override it to
        // implement a duration divisor.
        // To get around this, we copy the Potion's effects to be custom effects with the duration set accordingly,
        // and set the custom name to mimic the potion's.
        Optional<Holder<Potion>> originalPotion = original.potion();

        // potion will always be set to empty
        Optional<Integer> customColor = original.customColor();
        Optional<String> customName = original.customName()
                .or(() -> originalPotion.map(Holder::value).map(Potion::name));
        ArrayList<MobEffectInstance> customEffects = new ArrayList<>();
        originalPotion.ifPresent(potion -> {
            for (MobEffectInstance effect : potion.value().getEffects()) {
                customEffects.add(new MobEffectInstance(
                        effect.getEffect(),
                        Math.max(effect.mapDuration(duration -> duration / DURATION_DIVISOR), 1),
                        effect.getAmplifier(),
                        effect.isAmbient(),
                        effect.isVisible()
                ));
            }

        });
        customEffects.addAll(original.customEffects());

        return new PotionContents(Optional.empty(), customColor, customEffects, customName);
    }

    public static ItemStack createItemStack(int count, Holder<Potion> potion) {
        final ItemStack output = new ItemStack(Reference.Items.POTION_SNOW_CONE.get(), count);
        output.set(DataComponents.POTION_CONTENTS, PotionConeItem.modifyEffects(new PotionContents(potion)));
        return output;
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack itemstack = super.getDefaultInstance();
        itemstack.set(DataComponents.POTION_CONTENTS, new PotionContents(Potions.WATER));
        return itemstack;
    }

    @Override
    public Component getName(ItemStack stack) {
        @Nullable PotionContents potioncontents = stack.get(DataComponents.POTION_CONTENTS);
        return potioncontents != null ? potioncontents.getName(this.descriptionId + ".effect.") : super.getName(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        @Nullable PotionContents potioncontents = stack.get(DataComponents.POTION_CONTENTS);
        if (potioncontents != null) {
            // Duration factor is already factored in the custom effects
            potioncontents.addPotionTooltip(tooltip::add, 1, context.tickRate());
        }
    }
}
