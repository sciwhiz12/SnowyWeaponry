package sciwhiz12.snowyweaponry.recipe;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import sciwhiz12.snowyweaponry.Reference;

public class PotionConeRecipe extends SpecialRecipe {
    public PotionConeRecipe(ResourceLocation idIn) {
        super(idIn);
    }

    @Override
    public boolean matches(CraftingInventory inv, World worldIn) {
        if ((inv.getWidth() * inv.getHeight()) < 5) {
            return false;
        }
        boolean hasPotion = false;
        int cones = 0;
        for (int i = 0; i < inv.getWidth(); ++i) {
            for (int j = 0; j < inv.getHeight(); ++j) {
                ItemStack itemstack = inv.getItem(i + j * inv.getWidth());
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
    public ItemStack assemble(CraftingInventory inv) {
        ItemStack potion = ItemStack.EMPTY;

        for (int i = 0; i < inv.getWidth(); ++i) {
            for (int j = 0; j < inv.getHeight(); ++j) {
                ItemStack stack = inv.getItem(i + j * inv.getWidth());
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
    public IRecipeSerializer<?> getSerializer() {
        return Reference.RecipeSerializers.POTION_CONE_RECIPE;
    }
}
