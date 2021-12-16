package tk.sciwhiz12.snowyweaponry.recipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import tk.sciwhiz12.snowyweaponry.Reference;

public class PotionConeRecipe extends CustomRecipe {
    public PotionConeRecipe(ResourceLocation id) {
        super(id);
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        if ((container.getWidth() * container.getHeight()) < 5) {
            return false;
        }
        boolean hasPotion = false;
        int cones = 0;
        for (int i = 0; i < container.getWidth(); ++i) {
            for (int j = 0; j < container.getHeight(); ++j) {
                ItemStack itemstack = container.getItem(i + j * container.getWidth());
                Item item = itemstack.getItem();
                if (item == Items.POTION || item == Items.SPLASH_POTION) {
                    if (!hasPotion) {
                        hasPotion = true;
                    } else {
                        return false;
                    }
                } else if (item == Reference.Items.SNOW_CONE) {
                    if (cones++ > 4) {
                        return false;
                    }
                }
            }
        }

        return hasPotion && cones == 4;
    }

    @Override
    public ItemStack assemble(CraftingContainer container) {
        ItemStack potion = ItemStack.EMPTY;

        for (int i = 0; i < container.getWidth(); ++i) {
            for (int j = 0; j < container.getHeight(); ++j) {
                ItemStack stack = container.getItem(i + j * container.getWidth());
                Item item = stack.getItem();
                if (item == Items.POTION || item == Items.SPLASH_POTION) {
                    potion = stack;
                }
            }
        }

        if (potion.isEmpty()) {
            return ItemStack.EMPTY;
        } else {
            ItemStack ret = new ItemStack(Reference.Items.POTION_SNOW_CONE, 4);
            PotionUtils.setPotion(ret, PotionUtils.getPotion(potion));
            PotionUtils.setCustomEffects(ret, PotionUtils.getCustomEffects(potion));
            return ret;
        }
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 5;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Reference.RecipeSerializers.POTION_CONE_RECIPE;
    }
}
