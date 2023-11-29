package tk.sciwhiz12.snowyweaponry.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import tk.sciwhiz12.snowyweaponry.Reference.Items;
import tk.sciwhiz12.snowyweaponry.SnowyWeaponry;

import static java.util.Objects.requireNonNull;
import static net.minecraft.client.renderer.block.model.ItemModelGenerator.LAYERS;

public class ItemModels extends ItemModelProvider {
    private final ResourceLocation ITEM_GENERATED = mcLoc("item/generated");

    public ItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SnowyWeaponry.MODID, existingFileHelper);
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

        getBuilder(Items.POTION_SNOW_CONE.getId().toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture(LAYERS.get(0), modLoc(ITEM_FOLDER + '/' + "potion_snow_cone_base"))
                .texture(LAYERS.get(1), modLoc(ITEM_FOLDER + '/' + "potion_snow_cone_overlay"));
    }

    void singleTextureItem(Item item) {
        final ResourceLocation itemName = name(item);
        singleTexture(
                itemName.getPath(),
                ITEM_GENERATED,
                LAYERS.get(0),
                modLoc(ModelProvider.ITEM_FOLDER + "/" + itemName.getPath())
        );
    }

    private static ResourceLocation name(Item item) {
        return requireNonNull(ForgeRegistries.ITEMS.getKey(item));
    }
}
