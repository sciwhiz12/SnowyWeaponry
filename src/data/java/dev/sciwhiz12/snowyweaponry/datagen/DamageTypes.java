package dev.sciwhiz12.snowyweaponry.datagen;

import dev.sciwhiz12.snowyweaponry.Reference;
import dev.sciwhiz12.snowyweaponry.SnowyWeaponry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class DamageTypes extends DamageTypeTagsProvider {
    public DamageTypes(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, SnowyWeaponry.MODID, existingFileHelper);
    }

    protected void addTags(HolderLookup.Provider lookupProvider) {
        this.tag(DamageTypeTags.IS_EXPLOSION).add(Reference.DamageTypes.CORED_SNOWBALL_EXPLOSION);
        this.tag(DamageTypeTags.IS_FREEZING).add(Reference.DamageTypes.CORED_SNOWBALL, Reference.DamageTypes.CORED_SNOWBALL_EXPLOSION);
        this.tag(DamageTypeTags.IS_PROJECTILE).add(Reference.DamageTypes.CORED_SNOWBALL);
    }
}
