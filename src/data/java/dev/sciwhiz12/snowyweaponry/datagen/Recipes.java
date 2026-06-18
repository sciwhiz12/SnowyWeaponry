package dev.sciwhiz12.snowyweaponry.datagen;

import dev.sciwhiz12.snowyweaponry.Reference;
import dev.sciwhiz12.snowyweaponry.SnowyWeaponry;
import net.minecraft.advancements.predicates.ItemPredicate.Builder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.ImbueRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.advancements.triggers.InventoryChangeTrigger.TriggerInstance.hasItems;
import static net.minecraft.data.recipes.CustomCraftingRecipeBuilder.customCrafting;

public class Recipes extends RecipeProvider {
    private final RegistryLookup<Item> items;

    public Recipes(HolderLookup.Provider lookupProvider, RecipeOutput output) {
        super(lookupProvider, output);
        this.items = lookupProvider.lookupOrThrow(Registries.ITEM);
    }

    @Override
    protected void buildRecipes() {
        shapeless(RecipeCategory.MISC, Reference.Items.DIAMOND_CHUNK, 9)
                .requires(Tags.Items.GEMS_DIAMOND)
                .unlockedBy("has_diamond", hasItems(Reference.Items.DIAMOND_CHUNK))
                .save(output);
        shaped(RecipeCategory.MISC, Items.DIAMOND)
                .pattern("nnn")
                .pattern("nnn")
                .pattern("nnn")
                .define('n', Reference.Tags.NUGGETS_DIAMOND)
                .unlockedBy("has_diamond_nuggets", hasItems(Builder.item().of(this.items, Reference.Tags.NUGGETS_DIAMOND).build()))
                .save(output, SnowyWeaponry.id("diamond_from_nuggets").toString());

        shapeless(RecipeCategory.MISC, Reference.Items.NETHERITE_NUGGET, 9)
                .requires(Tags.Items.INGOTS_NETHERITE)
                .unlockedBy("has_netherite", hasItems(Reference.Items.NETHERITE_NUGGET))
                .save(output);
        shaped(RecipeCategory.MISC, Items.NETHERITE_INGOT)
                .pattern("nnn")
                .pattern("nnn")
                .pattern("nnn")
                .define('n', Reference.Tags.NUGGETS_NETHERITE)
                .unlockedBy("has_netherite_nuggets", hasItems(Builder.item().of(this.items, Reference.Tags.NUGGETS_NETHERITE).build()))
                .save(output, SnowyWeaponry.id("netherite_from_nuggets").toString());

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
                .requires(Tags.Items.GUNPOWDERS)
                .requires(Tags.Items.GUNPOWDERS)
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
                .unlockedBy("has_wheat", hasItems(Builder.item().of(this.items, Tags.Items.CROPS_WHEAT).build()))
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
        customCrafting(
                        RecipeCategory.MISC,
                        (commonInfo, bookInfo) -> new ImbueRecipe(
                                commonInfo,
                                bookInfo,
                                Ingredient.of(Items.POTION, Items.SPLASH_POTION),
                                Ingredient.of(Reference.Items.SNOW_CONE),
                                new ItemStackTemplate(Reference.Items.POTION_SNOW_CONE, 8)
                        )
                )
                .unlockedBy("has_snow_cone", this.has(Reference.Items.SNOW_CONE))
                .save(this.output, "potion_cone");
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(packOutput, lookupProvider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider lookupProvider, RecipeOutput recipeOutput) {
            return new Recipes(lookupProvider, recipeOutput);
        }

        @Override
        public String getName() {
            return SnowyWeaponry.MODID + " Recipes";
        }
    }

}
