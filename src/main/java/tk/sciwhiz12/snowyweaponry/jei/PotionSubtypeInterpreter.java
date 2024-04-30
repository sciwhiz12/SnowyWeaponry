package tk.sciwhiz12.snowyweaponry.jei;

import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import org.checkerframework.checker.nullness.qual.Nullable;

public class PotionSubtypeInterpreter implements IIngredientSubtypeInterpreter<ItemStack> {
    public static final PotionSubtypeInterpreter INSTANCE = new PotionSubtypeInterpreter();

    private PotionSubtypeInterpreter() {
    }

    @Override
    public String apply(ItemStack stack, UidContext context) {
        final @Nullable PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);
        if (contents == null) {
            return NONE;
        }

        final StringBuilder stringBuilder = new StringBuilder(contents.potion().map(Holder::getRegisteredName).orElse(""));

        for (MobEffectInstance effect : contents.customEffects()) {
            stringBuilder.append(";").append(effect);
        }

        return stringBuilder.toString();
    }
}
