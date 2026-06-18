package dev.sciwhiz12.snowyweaponry.datagen;

import dev.sciwhiz12.snowyweaponry.Reference;
import dev.sciwhiz12.snowyweaponry.SnowyWeaponry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityTypeIds;

import java.util.concurrent.CompletableFuture;

public class EntityTags extends EntityTypeTagsProvider {
    public EntityTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, SnowyWeaponry.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {
        tag(Reference.Tags.FIRE_MOBS)
                .add(EntityTypeIds.BLAZE);
    }
}
