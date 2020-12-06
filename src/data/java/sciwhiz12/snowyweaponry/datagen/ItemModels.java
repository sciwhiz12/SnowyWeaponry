package sciwhiz12.snowyweaponry.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import sciwhiz12.snowyweaponry.SnowyWeaponry;

import static java.util.Objects.requireNonNull;
import static net.minecraft.client.renderer.model.ItemModelGenerator.LAYERS;
import static sciwhiz12.snowyweaponry.Reference.Items;

public class ItemModels extends ItemModelProvider {
    private final ResourceLocation ITEM_GENERATED = mcLoc("item/generated");

    public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SnowyWeaponry.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        singleTextureItem(Items.DIAMOND_CHUNK);
        singleTextureItem(Items.NETHERITE_NUGGET);

        singleTextureItem(Items.IRON_CORED_SNOWBALL);
        singleTextureItem(Items.GOLD_CORED_SNOWBALL);
        singleTextureItem(Items.DIAMOND_CORED_SNOWBALL);
        singleTextureItem(Items.NETHERITE_CORED_SNOWBALL);
        singleTextureItem(Items.EXPLOSIVE_SNOWBALL);

        singleTextureItem(Items.WAFER_CONE);
        singleTextureItem(Items.SNOW_CONE);
        singleTextureItem(Items.GOLDEN_SNOW_CONE);

        itemWithOverlay(Items.POTION_SNOW_CONE, Items.SNOW_CONE, "potion_snow_cone_overlay");
    }

    void singleTextureItem(Item item) {
        final ResourceLocation itemName = requireNonNull(item.getRegistryName());
        singleTexture(
            itemName.getPath(),
            ITEM_GENERATED,
            LAYERS.get(0),
            modLoc(ModelProvider.ITEM_FOLDER + "/" + itemName.getPath())
        );
    }

    @SuppressWarnings("SameParameterValue")
    void itemWithOverlay(Item item, Item baseItem, String overlayTexture) {
        final ResourceLocation itemName = requireNonNull(item.getRegistryName());
        final ResourceLocation baseItemName = requireNonNull(baseItem.getRegistryName());
        withExistingParent(itemName.getPath(), ITEM_GENERATED)
            .texture(LAYERS.get(0), modLoc(ModelProvider.ITEM_FOLDER + "/" + baseItemName.getPath()))
            .texture(LAYERS.get(1), modLoc(ModelProvider.ITEM_FOLDER + "/" + overlayTexture));
    }
}
