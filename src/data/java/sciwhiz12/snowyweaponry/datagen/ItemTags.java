package sciwhiz12.snowyweaponry.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.TagsProvider;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import sciwhiz12.snowyweaponry.Reference;
import sciwhiz12.snowyweaponry.SnowyWeaponry;

import javax.annotation.Nullable;
import java.nio.file.Path;

public class ItemTags extends TagsProvider<Item> {
    @SuppressWarnings("deprecation")
    public ItemTags(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, Registry.ITEM, SnowyWeaponry.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(Tags.Items.NUGGETS)
            .addTag(Reference.Tags.NUGGETS_DIAMOND)
            .addTag(Reference.Tags.NUGGETS_NETHERITE);

        tag(Reference.Tags.NUGGETS_DIAMOND)
            .add(Reference.Items.DIAMOND_CHUNK);
        tag(Reference.Tags.NUGGETS_NETHERITE)
            .add(Reference.Items.NETHERITE_NUGGET);
    }

    // Copied from ItemTagsProvider#makePath
    protected Path getPath(ResourceLocation id) {
        return this.generator.getOutputFolder().resolve("data/" + id.getNamespace() + "/tags/items/" + id.getPath() + ".json");
    }

    public String getName() {
        return "Item Tags";
    }
}
