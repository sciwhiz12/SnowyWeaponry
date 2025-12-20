package dev.sciwhiz12.snowyweaponry.recipe;

import dev.sciwhiz12.snowyweaponry.Reference;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class PotionConeRecipe extends CustomRecipe {
    public PotionConeRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        if ((input.width() * input.height()) < 5) {
            return false;
        }
        boolean hasPotion = false;
        int cones = 0;
        for (int i = 0; i < input.width(); ++i) {
            for (int j = 0; j < input.height(); ++j) {
                ItemStack itemstack = input.getItem(i + j * input.width());
                Item item = itemstack.getItem();
                if (item == Items.POTION || item == Items.SPLASH_POTION) {
                    if (!hasPotion) {
                        hasPotion = true;
                    } else {
                        return false;
                    }
                } else if (item == Reference.Items.SNOW_CONE.get()) {
                    if (cones++ > 4) {
                        return false;
                    }
                }
            }
        }

        return hasPotion && cones == 4;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider holderLookup) {
        ItemStack potion = ItemStack.EMPTY;

        for (int i = 0; i < input.width(); ++i) {
            for (int j = 0; j < input.height(); ++j) {
                ItemStack stack = input.getItem(i + j * input.width());
                Item item = stack.getItem();
                if (item == Items.POTION || item == Items.SPLASH_POTION) {
                    potion = stack;
                }
            }
        }

        if (potion.isEmpty()) {
            return ItemStack.EMPTY;
        } else {
            ItemStack ret = Reference.Items.POTION_SNOW_CONE.toStack(4);
            PotionContents potionContents = potion.get(DataComponents.POTION_CONTENTS);
            if (potionContents != null) {
                ret.set(DataComponents.POTION_CONTENTS, potionContents);
            }
            return ret;
        }
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> remaining = NonNullList.withSize(input.size(), ItemStack.EMPTY);

        for (int i = 0; i < remaining.size(); ++i) {
            ItemStack item = input.getItem(i);
            if (item.is(Items.POTION)) { // Special case: remaining item for potion bottle is a glass bottle
                remaining.set(i, new ItemStack(Items.GLASS_BOTTLE));
            } else {
                remaining.set(i, item.getCraftingRemainder());
            }
        }

        return remaining;
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return Reference.RecipeSerializers.POTION_CONE_RECIPE.get();
    }
}
