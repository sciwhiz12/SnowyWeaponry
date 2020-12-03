package sciwhiz12.snowyweaponry.datagen;

import com.google.common.collect.Lists;
import net.minecraft.advancements.criterion.ItemPredicate.Builder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.CompoundIngredient;
import net.minecraftforge.common.crafting.NBTIngredient;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import sciwhiz12.snowyweaponry.Reference;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.function.Consumer;

import static net.minecraft.advancements.criterion.InventoryChangeTrigger.Instance.forItems;

public class Recipes extends RecipeProvider {
    private static final Constructor<CompoundIngredient> COMPOUND_INGREDIENT_CTOR = ObfuscationReflectionHelper
        .findConstructor(CompoundIngredient.class, List.class);

    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapelessRecipe(Reference.Items.DIAMOND_CHUNK, 9)
            .addIngredient(Tags.Items.GEMS_DIAMOND)
            .addCriterion("has_diamond", forItems(Reference.Items.DIAMOND_CHUNK))
            .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(Items.DIAMOND)
            .patternLine("nnn")
            .patternLine("nnn")
            .patternLine("nnn")
            .key('n', Reference.Tags.NUGGETS_DIAMOND)
            .addCriterion("has_diamond_nuggets", forItems(Builder.create().tag(Reference.Tags.NUGGETS_DIAMOND).build()))
            .build(consumer, "diamond_from_nuggets");

        ShapelessRecipeBuilder.shapelessRecipe(Reference.Items.NETHERITE_NUGGET, 9)
            .addIngredient(Tags.Items.INGOTS_NETHERITE)
            .addCriterion("has_netherite", forItems(Reference.Items.NETHERITE_NUGGET))
            .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(Items.NETHERITE_INGOT)
            .patternLine("nnn")
            .patternLine("nnn")
            .patternLine("nnn")
            .key('n', Reference.Tags.NUGGETS_NETHERITE)
            .addCriterion("has_netherite_nuggets", forItems(Builder.create().tag(Reference.Tags.NUGGETS_NETHERITE).build()))
            .build(consumer, "netherite_from_nuggets");

        registerSnowballs(consumer);

        registerSnowCones(consumer);
    }

    void registerSnowballs(Consumer<IFinishedRecipe> consumer) {
        final String cored_snowballs = "cored_snowballs";

        ShapedRecipeBuilder.shapedRecipe(Reference.Items.IRON_CORED_SNOWBALL, 8)
            .patternLine("sss")
            .patternLine("sIs")
            .patternLine("sss")
            .key('s', Items.SNOWBALL)
            .key('I', Tags.Items.NUGGETS_IRON)
            .addCriterion("has_snowball", forItems(Items.SNOWBALL))
            .setGroup(cored_snowballs)
            .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(Reference.Items.GOLD_CORED_SNOWBALL, 8)
            .patternLine("sss")
            .patternLine("sGs")
            .patternLine("sss")
            .key('s', Items.SNOWBALL)
            .key('G', Tags.Items.NUGGETS_GOLD)
            .addCriterion("has_snowball", forItems(Items.SNOWBALL))
            .setGroup(cored_snowballs)
            .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(Reference.Items.DIAMOND_CORED_SNOWBALL, 8)
            .patternLine("sss")
            .patternLine("sDs")
            .patternLine("sss")
            .key('s', Items.SNOWBALL)
            .key('D', Reference.Tags.NUGGETS_DIAMOND)
            .addCriterion("has_snowball", forItems(Items.SNOWBALL))
            .setGroup(cored_snowballs)
            .build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(Reference.Items.NETHERITE_CORED_SNOWBALL, 3)
            .addIngredient(Items.SNOWBALL)
            .addIngredient(Items.SNOWBALL)
            .addIngredient(Items.SNOWBALL)
            .addIngredient(Reference.Tags.NUGGETS_NETHERITE)
            .addCriterion("has_snowball", forItems(Items.SNOWBALL))
            .setGroup(cored_snowballs)
            .build(consumer);
    }

    void registerSnowCones(Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapelessRecipe(Reference.Items.WAFER_CONE, 4)
            .addIngredient(Tags.Items.CROPS_WHEAT)
            .addIngredient(Tags.Items.CROPS_WHEAT)
            .addIngredient(Tags.Items.CROPS_WHEAT)
            .addIngredient(Items.WATER_BUCKET)
            .addCriterion("has_wheat", forItems(Builder.create().tag(Tags.Items.CROPS_WHEAT).build()))
            .build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(Reference.Items.SNOW_CONE)
            .addIngredient(Reference.Items.WAFER_CONE)
            .addIngredient(Items.SNOWBALL)
            .addIngredient(Items.SNOWBALL)
            .addCriterion("has_wafer_cone", forItems(Reference.Items.WAFER_CONE))
            .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(Reference.Items.GOLDEN_SNOW_CONE)
            .patternLine("ggg")
            .patternLine("gSg")
            .patternLine("ggg")
            .key('g', Tags.Items.NUGGETS_GOLD)
            .key('S', Reference.Items.SNOW_CONE)
            .addCriterion("has_snow_cone", forItems(Reference.Items.SNOW_CONE))
            .build(consumer);
    }

    private static CompoundIngredient compound(Ingredient... ingredients) {
        return new CompoundIngredient(Lists.newArrayList(ingredients)) {};
    }

    private static NBTIngredient nbt(ItemStack stack) {
        return new NBTIngredient(stack) {};
    }
}
