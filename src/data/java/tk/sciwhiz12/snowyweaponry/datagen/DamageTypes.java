package tk.sciwhiz12.snowyweaponry.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import tk.sciwhiz12.snowyweaponry.Reference;

import java.util.concurrent.CompletableFuture;

import static tk.sciwhiz12.snowyweaponry.SnowyWeaponry.MODID;

public class DamageTypes extends DamageTypeTagsProvider {
    public DamageTypes(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MODID, existingFileHelper);
    }

    protected void addTags(HolderLookup.Provider lookupProvider) {
        this.tag(DamageTypeTags.IS_EXPLOSION).add(Reference.DamageTypes.CORED_SNOWBALL_EXPLOSION);
        this.tag(DamageTypeTags.IS_FREEZING).add(Reference.DamageTypes.CORED_SNOWBALL, Reference.DamageTypes.CORED_SNOWBALL_EXPLOSION);
        this.tag(DamageTypeTags.IS_PROJECTILE).add(Reference.DamageTypes.CORED_SNOWBALL);
    }
}
