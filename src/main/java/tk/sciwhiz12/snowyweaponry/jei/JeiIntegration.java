package tk.sciwhiz12.snowyweaponry.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.neoforged.neoforge.common.crafting.NBTIngredient;
import tk.sciwhiz12.snowyweaponry.Reference;
import tk.sciwhiz12.snowyweaponry.SnowyWeaponry;

import java.util.List;
import java.util.Map;

@JeiPlugin
public class JeiIntegration implements IModPlugin {
    private static final ResourceLocation PLUGIN_UID = SnowyWeaponry.loc("jei_plugin");

    public JeiIntegration() { // Required for JEI plugins, see annotation javadocs
    }

    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_UID;
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(Reference.Items.POTION_SNOW_CONE.get(), PotionSubtypeInterpreter.INSTANCE);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        final List<RecipeHolder<CraftingRecipe>> recipes = createPotionConeRecipes();
        registration.addRecipes(RecipeTypes.CRAFTING, recipes);
    }

    private static List<RecipeHolder<CraftingRecipe>> createPotionConeRecipes() {
        final Ingredient cone = Ingredient.of(Reference.Items.SNOW_CONE);

        return BuiltInRegistries.POTION.stream()
                .map(potion -> {
                    final ItemStack input = PotionUtils.setPotion(new ItemStack(Items.POTION), potion);
                    final Ingredient potionIngredient = NBTIngredient.of(false, input.getOrCreateTag(), Items.POTION, Items.SPLASH_POTION);
                    final ItemStack output = PotionUtils.setPotion(new ItemStack(Reference.Items.POTION_SNOW_CONE.get(), 4), potion);

                    final ShapedRecipePattern pattern = ShapedRecipePattern.of(
                            Map.of(
                                    'C', cone,
                                    'P', potionIngredient
                            ),
                            List.of(" C ",
                                    "CPC",
                                    " C ")
                    );
                    return new RecipeHolder<CraftingRecipe>(SnowyWeaponry.loc(output.getDescriptionId()),
                            new ShapedRecipe(output.getDescriptionId(), CraftingBookCategory.MISC, pattern, output));
                })
                .toList();
    }
}
