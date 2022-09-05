package tk.sciwhiz12.snowyweaponry.jei;

import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;

public class PotionSubtypeInterpreter implements IIngredientSubtypeInterpreter<ItemStack> {
    public static final PotionSubtypeInterpreter INSTANCE = new PotionSubtypeInterpreter();

    private PotionSubtypeInterpreter() {
    }

    @Override
    public String apply(ItemStack stack, UidContext context) {
        if (!stack.hasTag()) {
            return NONE;
        }

        final Potion potion = PotionUtils.getPotion(stack);
        final StringBuilder stringBuilder = new StringBuilder(potion.getName(""));

        for (MobEffectInstance effect : PotionUtils.getMobEffects(stack)) {
            stringBuilder.append(";").append(effect);
        }

        return stringBuilder.toString();
    }
}
