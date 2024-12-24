package dev.sciwhiz12.snowyweaponry.datagen;

import dev.sciwhiz12.snowyweaponry.Reference.Items;
import dev.sciwhiz12.snowyweaponry.SnowyWeaponry;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class Models extends ModelProvider {
    public Models(PackOutput output) {
        super(output, SnowyWeaponry.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(Items.DIAMOND_CHUNK.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(Items.NETHERITE_NUGGET.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(Items.IRON_CORED_SNOWBALL.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(Items.GOLD_CORED_SNOWBALL.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(Items.DIAMOND_CORED_SNOWBALL.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(Items.NETHERITE_CORED_SNOWBALL.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(Items.EXPLOSIVE_SNOWBALL.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(Items.WAFER_CONE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(Items.SNOW_CONE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(Items.GOLDEN_SNOW_CONE.get(), ModelTemplates.FLAT_ITEM);

        generatePotionCone(itemModels, Items.POTION_SNOW_CONE.get());
    }

    private void generatePotionCone(ItemModelGenerators itemModels, Item item) {
        ResourceLocation layeredModel = itemModels.generateLayeredItem(
                item,
                ModelLocationUtils.getModelLocation(item, "_overlay"),
                ModelLocationUtils.getModelLocation(item, "_base")
        );
        itemModels.addPotionTint(item, layeredModel);
    }
}
