package dev.sciwhiz12.snowyweaponry.jei;

import dev.sciwhiz12.snowyweaponry.Reference;
import dev.sciwhiz12.snowyweaponry.SnowyWeaponry;
import dev.sciwhiz12.snowyweaponry.item.PotionConeItem;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.neoforged.neoforge.common.crafting.DataComponentIngredient;

import java.util.List;
import java.util.Map;

@JeiPlugin
public class JeiIntegration implements IModPlugin {
    private static final Identifier PLUGIN_UID = SnowyWeaponry.id("jei_plugin");

    public JeiIntegration() { // Required for JEI plugins, see annotation javadocs
    }

    @Override
    public Identifier getPluginUid() {
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

        return BuiltInRegistries.POTION.listElements()
                .map(potion -> {
                    final Ingredient potionIngredient = DataComponentIngredient.of(false,
                            DataComponentExactPredicate.expect(DataComponents.POTION_CONTENTS, new PotionContents(potion)),
                            Items.POTION, Items.SPLASH_POTION
                    );
                    final ItemStack output = PotionConeItem.createItemStack(4, potion);

                    final ShapedRecipePattern pattern = ShapedRecipePattern.of(
                            Map.of(
                                    'C', cone,
                                    'P', potionIngredient
                            ),
                            List.of(" C ",
                                    "CPC",
                                    " C ")
                    );
                    return new RecipeHolder<CraftingRecipe>(
                            ResourceKey.create(Registries.RECIPE, SnowyWeaponry.id(output.getItem().getDescriptionId())),
                            new ShapedRecipe(output.getItem().getDescriptionId(), CraftingBookCategory.MISC, pattern, output)
                    );
                })
                .toList();
    }
}
