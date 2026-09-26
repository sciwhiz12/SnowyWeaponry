package dev.sciwhiz12.snowyweaponry.datagen;

import dev.sciwhiz12.snowyweaponry.Reference;
import dev.sciwhiz12.snowyweaponry.SnowyWeaponry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;

import java.util.concurrent.CompletableFuture;

public class DamageTypeTags extends DamageTypeTagsProvider {
    public DamageTypeTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, SnowyWeaponry.MODID);
    }

    @SuppressWarnings("unchecked")
    protected void addTags(HolderLookup.Provider lookupProvider) {
        this.tag(net.minecraft.tags.DamageTypeTags.IS_EXPLOSION).add(Reference.DamageTypes.CORED_SNOWBALL_EXPLOSION);
        this.tag(net.minecraft.tags.DamageTypeTags.IS_FREEZING).add(Reference.DamageTypes.CORED_SNOWBALL, Reference.DamageTypes.CORED_SNOWBALL_EXPLOSION);
        this.tag(net.minecraft.tags.DamageTypeTags.IS_PROJECTILE).add(Reference.DamageTypes.CORED_SNOWBALL);
    }
}
