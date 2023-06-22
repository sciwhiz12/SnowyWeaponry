package tk.sciwhiz12.snowyweaponry.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.common.data.ExistingFileHelper;
import tk.sciwhiz12.snowyweaponry.Reference;

import java.util.concurrent.CompletableFuture;

import static tk.sciwhiz12.snowyweaponry.SnowyWeaponry.MODID;

// Currently not using DamageTypeTagsProvider because it doesn't have the Forge-added mod-ID-sensitive constructor
public class DamageTypes extends TagsProvider<DamageType> {
    public DamageTypes(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, Registries.DAMAGE_TYPE, lookupProvider, MODID, existingFileHelper);
    }

    protected void addTags(HolderLookup.Provider lookupProvider) {
        this.tag(DamageTypeTags.IS_EXPLOSION).add(Reference.DamageTypes.CORED_SNOWBALL_EXPLOSION);
        this.tag(DamageTypeTags.IS_FREEZING).add(Reference.DamageTypes.CORED_SNOWBALL, Reference.DamageTypes.CORED_SNOWBALL_EXPLOSION);
        this.tag(DamageTypeTags.IS_PROJECTILE).add(Reference.DamageTypes.CORED_SNOWBALL);
    }
}
