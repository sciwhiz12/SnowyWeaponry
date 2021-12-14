package sciwhiz12.snowyweaponry.datagen;

import com.google.common.collect.Lists;
import net.minecraft.advancements.critereon.ItemPredicate.Builder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.CompoundIngredient;
import net.minecraftforge.common.crafting.NBTIngredient;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import sciwhiz12.snowyweaponry.Reference;
import sciwhiz12.snowyweaponry.Reference.RecipeSerializers;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.function.Consumer;

import static net.minecraft.advancements.critereon.InventoryChangeTrigger.TriggerInstance.hasItems;

public class Recipes extends RecipeProvider {
    private static final Constructor<CompoundIngredient> COMPOUND_INGREDIENT_CTOR = ObfuscationReflectionHelper
        .findConstructor(CompoundIngredient.class, List.class);

    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(Reference.Items.DIAMOND_CHUNK, 9)
            .requires(Tags.Items.GEMS_DIAMOND)
            .unlockedBy("has_diamond", hasItems(Reference.Items.DIAMOND_CHUNK))
            .save(consumer);
        ShapedRecipeBuilder.shaped(Items.DIAMOND)
            .pattern("nnn")
            .pattern("nnn")
            .pattern("nnn")
            .define('n', Reference.Tags.NUGGETS_DIAMOND)
            .unlockedBy("has_diamond_nuggets", hasItems(Builder.item().of(Reference.Tags.NUGGETS_DIAMOND).build()))
            .save(consumer, "diamond_from_nuggets");

        ShapelessRecipeBuilder.shapeless(Reference.Items.NETHERITE_NUGGET, 9)
            .requires(Tags.Items.INGOTS_NETHERITE)
            .unlockedBy("has_netherite", hasItems(Reference.Items.NETHERITE_NUGGET))
            .save(consumer);
        ShapedRecipeBuilder.shaped(Items.NETHERITE_INGOT)
            .pattern("nnn")
            .pattern("nnn")
            .pattern("nnn")
            .define('n', Reference.Tags.NUGGETS_NETHERITE)
            .unlockedBy("has_netherite_nuggets", hasItems(Builder.item().of(Reference.Tags.NUGGETS_NETHERITE).build()))
            .save(consumer, "netherite_from_nuggets");

        registerSnowballs(consumer);

        registerSnowCones(consumer);
    }

    void registerSnowballs(Consumer<FinishedRecipe> consumer) {
        final String cored_snowballs = "cored_snowballs";

        ShapedRecipeBuilder.shaped(Reference.Items.IRON_CORED_SNOWBALL, 8)
            .pattern("sss")
            .pattern("sIs")
            .pattern("sss")
            .define('s', Items.SNOWBALL)
            .define('I', Tags.Items.NUGGETS_IRON)
            .unlockedBy("has_snowball", hasItems(Items.SNOWBALL))
            .group(cored_snowballs)
            .save(consumer);
        ShapedRecipeBuilder.shaped(Reference.Items.GOLD_CORED_SNOWBALL, 8)
            .pattern("sss")
            .pattern("sGs")
            .pattern("sss")
            .define('s', Items.SNOWBALL)
            .define('G', Tags.Items.NUGGETS_GOLD)
            .unlockedBy("has_snowball", hasItems(Items.SNOWBALL))
            .group(cored_snowballs)
            .save(consumer);
        ShapedRecipeBuilder.shaped(Reference.Items.DIAMOND_CORED_SNOWBALL, 8)
            .pattern("sss")
            .pattern("sDs")
            .pattern("sss")
            .define('s', Items.SNOWBALL)
            .define('D', Reference.Tags.NUGGETS_DIAMOND)
            .unlockedBy("has_snowball", hasItems(Items.SNOWBALL))
            .group(cored_snowballs)
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(Reference.Items.NETHERITE_CORED_SNOWBALL, 3)
            .requires(Items.SNOWBALL)
            .requires(Items.SNOWBALL)
            .requires(Items.SNOWBALL)
            .requires(Reference.Tags.NUGGETS_NETHERITE)
            .unlockedBy("has_snowball", hasItems(Items.SNOWBALL))
            .group(cored_snowballs)
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(Reference.Items.EXPLOSIVE_SNOWBALL, 3)
            .requires(Items.SNOWBALL)
            .requires(Items.SNOWBALL)
            .requires(Items.SNOWBALL)
            .requires(Tags.Items.GUNPOWDER)
            .requires(Tags.Items.GUNPOWDER)
            .unlockedBy("has_snowball", hasItems(Items.SNOWBALL))
            .group(cored_snowballs)
            .save(consumer);
    }

    void registerSnowCones(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(Reference.Items.WAFER_CONE, 4)
            .requires(Tags.Items.CROPS_WHEAT)
            .requires(Tags.Items.CROPS_WHEAT)
            .requires(Tags.Items.CROPS_WHEAT)
            .requires(Items.WATER_BUCKET)
            .unlockedBy("has_wheat", hasItems(Builder.item().of(Tags.Items.CROPS_WHEAT).build()))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(Reference.Items.SNOW_CONE)
            .requires(Reference.Items.WAFER_CONE)
            .requires(Items.SNOWBALL)
            .requires(Items.SNOWBALL)
            .unlockedBy("has_wafer_cone", hasItems(Reference.Items.WAFER_CONE))
            .save(consumer);
        ShapedRecipeBuilder.shaped(Reference.Items.GOLDEN_SNOW_CONE)
            .pattern("ggg")
            .pattern("gSg")
            .pattern("ggg")
            .define('g', Tags.Items.NUGGETS_GOLD)
            .define('S', Reference.Items.SNOW_CONE)
            .unlockedBy("has_snow_cone", hasItems(Reference.Items.SNOW_CONE))
            .save(consumer);
        SpecialRecipeBuilder.special(RecipeSerializers.POTION_CONE_RECIPE)
            .save(consumer, String.valueOf(RecipeSerializers.POTION_CONE_RECIPE.getRegistryName()));
    }

    private static CompoundIngredient compound(Ingredient... ingredients) {
        return new CompoundIngredient(Lists.newArrayList(ingredients)) {
        };
    }

    private static NBTIngredient nbt(ItemStack stack) {
        return new NBTIngredient(stack) {
        };
    }
}
