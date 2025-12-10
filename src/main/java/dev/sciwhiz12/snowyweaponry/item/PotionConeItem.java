package dev.sciwhiz12.snowyweaponry.item;

import dev.sciwhiz12.snowyweaponry.Reference.Items;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import org.jspecify.annotations.Nullable;

public class PotionConeItem extends Item {
    public static final int DURATION_DIVISOR = 8;

    public PotionConeItem(Item.Properties properties) {
        super(properties);
    }

    public static ItemStack createItemStack(int count, Holder<Potion> potion) {
        final ItemStack output = Items.POTION_SNOW_CONE.toStack(count);
        output.set(DataComponents.POTION_CONTENTS, new PotionContents(potion));
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
}
