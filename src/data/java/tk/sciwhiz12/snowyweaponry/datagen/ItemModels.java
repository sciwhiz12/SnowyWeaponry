package tk.sciwhiz12.snowyweaponry.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import tk.sciwhiz12.snowyweaponry.Reference.Items;
import tk.sciwhiz12.snowyweaponry.SnowyWeaponry;

import static java.util.Objects.requireNonNull;
import static net.minecraft.client.renderer.block.model.ItemModelGenerator.LAYERS;

public class ItemModels extends ItemModelProvider {
    private final ResourceLocation ITEM_GENERATED = mcLoc("item/generated");

    public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SnowyWeaponry.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        singleTextureItem(Items.DIAMOND_CHUNK.get());
        singleTextureItem(Items.NETHERITE_NUGGET.get());

        singleTextureItem(Items.IRON_CORED_SNOWBALL.get());
        singleTextureItem(Items.GOLD_CORED_SNOWBALL.get());
        singleTextureItem(Items.DIAMOND_CORED_SNOWBALL.get());
        singleTextureItem(Items.NETHERITE_CORED_SNOWBALL.get());
        singleTextureItem(Items.EXPLOSIVE_SNOWBALL.get());

        singleTextureItem(Items.WAFER_CONE.get());
        singleTextureItem(Items.SNOW_CONE.get());
        singleTextureItem(Items.GOLDEN_SNOW_CONE.get());

        itemWithOverlay(Items.POTION_SNOW_CONE.get(), Items.SNOW_CONE.get(), "potion_snow_cone_overlay");
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
