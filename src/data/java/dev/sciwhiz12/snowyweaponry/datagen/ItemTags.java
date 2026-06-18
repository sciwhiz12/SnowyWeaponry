package dev.sciwhiz12.snowyweaponry.datagen;

import dev.sciwhiz12.snowyweaponry.Reference;
import dev.sciwhiz12.snowyweaponry.SnowyWeaponry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ItemTags extends ItemTagsProvider {
    public ItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, SnowyWeaponry.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {
        tag(Tags.Items.NUGGETS)
                .addTag(Reference.Tags.NUGGETS_DIAMOND)
                .addTag(Reference.Tags.NUGGETS_NETHERITE);

        tag(Reference.Tags.NUGGETS_DIAMOND)
                .add(Reference.Items.DIAMOND_CHUNK.getKey());
        tag(Reference.Tags.NUGGETS_NETHERITE)
                .add(Reference.Items.NETHERITE_NUGGET.getKey());
    }

    public String getName() {
        return "Item Tags";
    }
}
