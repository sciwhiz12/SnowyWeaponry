package sciwhiz12.snowyweaponry.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import sciwhiz12.snowyweaponry.SnowyWeaponry;

import static java.util.Objects.requireNonNull;
import static sciwhiz12.snowyweaponry.Reference.Items;

public class ItemModels extends ItemModelProvider {
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
    }

    void singleTextureItem(Item i) {
        requireNonNull(i.getRegistryName());
        singleTexture(
            i.getRegistryName().getPath(),
            mcLoc("item/generated"),
            "layer0",
            modLoc(ModelProvider.ITEM_FOLDER + "/" + i.getRegistryName().getPath())
        );
    }
}
