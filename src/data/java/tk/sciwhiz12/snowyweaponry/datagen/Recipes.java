package tk.sciwhiz12.snowyweaponry.datagen;

import net.minecraft.advancements.critereon.ItemPredicate.Builder;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import tk.sciwhiz12.snowyweaponry.Reference;
import tk.sciwhiz12.snowyweaponry.Reference.RecipeSerializers;

import java.util.function.Consumer;

import static net.minecraft.advancements.critereon.InventoryChangeTrigger.TriggerInstance.hasItems;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.shapeless;
import static net.minecraft.data.recipes.SpecialRecipeBuilder.special;

public class Recipes extends RecipeProvider {
    public Recipes(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        shapeless(RecipeCategory.MISC, Reference.Items.DIAMOND_CHUNK.get(), 9)
                .requires(Tags.Items.GEMS_DIAMOND)
                .unlockedBy("has_diamond", hasItems(Reference.Items.DIAMOND_CHUNK.get()))
                .save(consumer);
        shaped(RecipeCategory.MISC, Items.DIAMOND)
                .pattern("nnn")
                .pattern("nnn")
                .pattern("nnn")
                .define('n', Reference.Tags.NUGGETS_DIAMOND)
                .unlockedBy("has_diamond_nuggets", hasItems(Builder.item().of(Reference.Tags.NUGGETS_DIAMOND).build()))
                .save(consumer, "diamond_from_nuggets");

        shapeless(RecipeCategory.MISC, Reference.Items.NETHERITE_NUGGET.get(), 9)
                .requires(Tags.Items.INGOTS_NETHERITE)
                .unlockedBy("has_netherite", hasItems(Reference.Items.NETHERITE_NUGGET.get()))
                .save(consumer);
        shaped(RecipeCategory.MISC, Items.NETHERITE_INGOT)
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

        shaped(RecipeCategory.COMBAT, Reference.Items.IRON_CORED_SNOWBALL.get(), 8)
                .pattern("sss")
                .pattern("sIs")
                .pattern("sss")
                .define('s', Items.SNOWBALL)
                .define('I', Tags.Items.NUGGETS_IRON)
                .unlockedBy("has_snowball", hasItems(Items.SNOWBALL))
                .group(cored_snowballs)
                .save(consumer);
        shaped(RecipeCategory.COMBAT, Reference.Items.GOLD_CORED_SNOWBALL.get(), 8)
                .pattern("sss")
                .pattern("sGs")
                .pattern("sss")
                .define('s', Items.SNOWBALL)
                .define('G', Tags.Items.NUGGETS_GOLD)
                .unlockedBy("has_snowball", hasItems(Items.SNOWBALL))
                .group(cored_snowballs)
                .save(consumer);
        shaped(RecipeCategory.COMBAT, Reference.Items.DIAMOND_CORED_SNOWBALL.get(), 8)
                .pattern("sss")
                .pattern("sDs")
                .pattern("sss")
                .define('s', Items.SNOWBALL)
                .define('D', Reference.Tags.NUGGETS_DIAMOND)
                .unlockedBy("has_snowball", hasItems(Items.SNOWBALL))
                .group(cored_snowballs)
                .save(consumer);
        shapeless(RecipeCategory.COMBAT, Reference.Items.NETHERITE_CORED_SNOWBALL.get(), 3)
                .requires(Items.SNOWBALL)
                .requires(Items.SNOWBALL)
                .requires(Items.SNOWBALL)
                .requires(Reference.Tags.NUGGETS_NETHERITE)
                .unlockedBy("has_snowball", hasItems(Items.SNOWBALL))
                .group(cored_snowballs)
                .save(consumer);
        shapeless(RecipeCategory.COMBAT, Reference.Items.EXPLOSIVE_SNOWBALL.get(), 3)
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
        shapeless(RecipeCategory.FOOD, Reference.Items.WAFER_CONE.get(), 4)
                .requires(Tags.Items.CROPS_WHEAT)
                .requires(Tags.Items.CROPS_WHEAT)
                .requires(Tags.Items.CROPS_WHEAT)
                .requires(Items.WATER_BUCKET)
                .unlockedBy("has_wheat", hasItems(Builder.item().of(Tags.Items.CROPS_WHEAT).build()))
                .save(consumer);
        shapeless(RecipeCategory.FOOD, Reference.Items.SNOW_CONE.get())
                .requires(Reference.Items.WAFER_CONE.get())
                .requires(Items.SNOWBALL)
                .requires(Items.SNOWBALL)
                .unlockedBy("has_wafer_cone", hasItems(Reference.Items.WAFER_CONE.get()))
                .save(consumer);
        shaped(RecipeCategory.FOOD, Reference.Items.GOLDEN_SNOW_CONE.get())
                .pattern("ggg")
                .pattern("gSg")
                .pattern("ggg")
                .define('g', Tags.Items.NUGGETS_GOLD)
                .define('S', Reference.Items.SNOW_CONE.get())
                .unlockedBy("has_snow_cone", hasItems(Reference.Items.SNOW_CONE.get()))
                .save(consumer);
        special(RecipeSerializers.POTION_CONE_RECIPE.get())
                .save(consumer, RecipeSerializers.POTION_CONE_RECIPE.getId().toString());
    }
}
