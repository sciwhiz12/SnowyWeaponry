package tk.sciwhiz12.snowyweaponry.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import tk.sciwhiz12.snowyweaponry.Reference.Items;
import tk.sciwhiz12.snowyweaponry.SnowyWeaponry;

import static net.minecraft.client.renderer.block.model.ItemModelGenerator.LAYERS;

public class ItemModels extends ItemModelProvider {
    public ItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SnowyWeaponry.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(Items.DIAMOND_CHUNK.get());
        basicItem(Items.NETHERITE_NUGGET.get());

        basicItem(Items.IRON_CORED_SNOWBALL.get());
        basicItem(Items.GOLD_CORED_SNOWBALL.get());
        basicItem(Items.DIAMOND_CORED_SNOWBALL.get());
        basicItem(Items.NETHERITE_CORED_SNOWBALL.get());
        basicItem(Items.EXPLOSIVE_SNOWBALL.get());

        basicItem(Items.WAFER_CONE.get());
        basicItem(Items.SNOW_CONE.get());
        basicItem(Items.GOLDEN_SNOW_CONE.get());

        getBuilder(Items.POTION_SNOW_CONE.getId().toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture(LAYERS.get(0), modLoc(ITEM_FOLDER + '/' + "potion_snow_cone_base"))
                .texture(LAYERS.get(1), modLoc(ITEM_FOLDER + '/' + "potion_snow_cone_overlay"));
    }
}
