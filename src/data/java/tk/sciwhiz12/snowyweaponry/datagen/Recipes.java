package tk.sciwhiz12.snowyweaponry.datagen;

import net.minecraft.advancements.critereon.ItemPredicate.Builder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
import tk.sciwhiz12.snowyweaponry.Reference;
import tk.sciwhiz12.snowyweaponry.Reference.RecipeSerializers;
import tk.sciwhiz12.snowyweaponry.recipe.PotionConeRecipe;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.advancements.critereon.InventoryChangeTrigger.TriggerInstance.hasItems;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.shapeless;
import static net.minecraft.data.recipes.SpecialRecipeBuilder.special;

public class Recipes extends RecipeProvider {
    public Recipes(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        shapeless(RecipeCategory.MISC, Reference.Items.DIAMOND_CHUNK, 9)
                .requires(Tags.Items.GEMS_DIAMOND)
                .unlockedBy("has_diamond", hasItems(Reference.Items.DIAMOND_CHUNK))
                .save(output);
        shaped(RecipeCategory.MISC, Items.DIAMOND)
                .pattern("nnn")
                .pattern("nnn")
                .pattern("nnn")
                .define('n', Reference.Tags.NUGGETS_DIAMOND)
                .unlockedBy("has_diamond_nuggets", hasItems(Builder.item().of(Reference.Tags.NUGGETS_DIAMOND).build()))
                .save(output, "diamond_from_nuggets");

        shapeless(RecipeCategory.MISC, Reference.Items.NETHERITE_NUGGET, 9)
                .requires(Tags.Items.INGOTS_NETHERITE)
                .unlockedBy("has_netherite", hasItems(Reference.Items.NETHERITE_NUGGET))
                .save(output);
        shaped(RecipeCategory.MISC, Items.NETHERITE_INGOT)
                .pattern("nnn")
                .pattern("nnn")
                .pattern("nnn")
                .define('n', Reference.Tags.NUGGETS_NETHERITE)
                .unlockedBy("has_netherite_nuggets", hasItems(Builder.item().of(Reference.Tags.NUGGETS_NETHERITE).build()))
                .save(output, "netherite_from_nuggets");

        registerSnowballs(output);

        registerSnowCones(output);
    }

    void registerSnowballs(RecipeOutput output) {
        final String cored_snowballs = "cored_snowballs";

        shaped(RecipeCategory.COMBAT, Reference.Items.IRON_CORED_SNOWBALL, 8)
                .pattern("sss")
                .pattern("sIs")
                .pattern("sss")
                .define('s', Items.SNOWBALL)
                .define('I', Tags.Items.NUGGETS_IRON)
                .unlockedBy("has_snowball", hasItems(Items.SNOWBALL))
                .group(cored_snowballs)
                .save(output);
        shaped(RecipeCategory.COMBAT, Reference.Items.GOLD_CORED_SNOWBALL, 8)
                .pattern("sss")
                .pattern("sGs")
                .pattern("sss")
                .define('s', Items.SNOWBALL)
                .define('G', Tags.Items.NUGGETS_GOLD)
                .unlockedBy("has_snowball", hasItems(Items.SNOWBALL))
                .group(cored_snowballs)
                .save(output);
        shaped(RecipeCategory.COMBAT, Reference.Items.DIAMOND_CORED_SNOWBALL, 8)
                .pattern("sss")
                .pattern("sDs")
                .pattern("sss")
                .define('s', Items.SNOWBALL)
                .define('D', Reference.Tags.NUGGETS_DIAMOND)
                .unlockedBy("has_snowball", hasItems(Items.SNOWBALL))
                .group(cored_snowballs)
                .save(output);
        shapeless(RecipeCategory.COMBAT, Reference.Items.NETHERITE_CORED_SNOWBALL, 3)
                .requires(Items.SNOWBALL)
                .requires(Items.SNOWBALL)
                .requires(Items.SNOWBALL)
                .requires(Reference.Tags.NUGGETS_NETHERITE)
                .unlockedBy("has_snowball", hasItems(Items.SNOWBALL))
                .group(cored_snowballs)
                .save(output);
        shapeless(RecipeCategory.COMBAT, Reference.Items.EXPLOSIVE_SNOWBALL, 3)
                .requires(Items.SNOWBALL)
                .requires(Items.SNOWBALL)
                .requires(Items.SNOWBALL)
                .requires(Tags.Items.GUNPOWDER)
                .requires(Tags.Items.GUNPOWDER)
                .unlockedBy("has_snowball", hasItems(Items.SNOWBALL))
                .group(cored_snowballs)
                .save(output);
    }

    void registerSnowCones(RecipeOutput output) {
        shapeless(RecipeCategory.FOOD, Reference.Items.WAFER_CONE, 4)
                .requires(Tags.Items.CROPS_WHEAT)
                .requires(Tags.Items.CROPS_WHEAT)
                .requires(Tags.Items.CROPS_WHEAT)
                .requires(Items.WATER_BUCKET)
                .unlockedBy("has_wheat", hasItems(Builder.item().of(Tags.Items.CROPS_WHEAT).build()))
                .save(output);
        shapeless(RecipeCategory.FOOD, Reference.Items.SNOW_CONE)
                .requires(Reference.Items.WAFER_CONE)
                .requires(Items.SNOWBALL)
                .requires(Items.SNOWBALL)
                .unlockedBy("has_wafer_cone", hasItems(Reference.Items.WAFER_CONE))
                .save(output);
        shaped(RecipeCategory.FOOD, Reference.Items.GOLDEN_SNOW_CONE)
                .pattern("ggg")
                .pattern("gSg")
                .pattern("ggg")
                .define('g', Tags.Items.NUGGETS_GOLD)
                .define('S', Reference.Items.SNOW_CONE)
                .unlockedBy("has_snow_cone", hasItems(Reference.Items.SNOW_CONE))
                .save(output);
        special(PotionConeRecipe::new)
                .save(output, RecipeSerializers.POTION_CONE_RECIPE.getId().toString());
    }
}
