package dev.sciwhiz12.snowyweaponry.jei;

import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import org.checkerframework.checker.nullness.qual.Nullable;

public class PotionSubtypeInterpreter implements ISubtypeInterpreter<ItemStack> {
    public static final PotionSubtypeInterpreter INSTANCE = new PotionSubtypeInterpreter();

    private PotionSubtypeInterpreter() {
    }

    @Override
    public @Nullable Object getSubtypeData(ItemStack ingredient, UidContext context) {
        final @Nullable PotionContents contents = ingredient.get(DataComponents.POTION_CONTENTS);
        if (contents == null) return null;

        return contents.potion().orElse(null);
    }
}
