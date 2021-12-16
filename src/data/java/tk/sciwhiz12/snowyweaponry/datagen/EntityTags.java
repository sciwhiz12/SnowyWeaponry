package tk.sciwhiz12.snowyweaponry.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeRegistryTagsProvider;
import net.minecraftforge.registries.ForgeRegistries;
import org.checkerframework.checker.nullness.qual.Nullable;
import tk.sciwhiz12.snowyweaponry.Reference;
import tk.sciwhiz12.snowyweaponry.SnowyWeaponry;

public class EntityTags extends ForgeRegistryTagsProvider<EntityType<?>> {
    public EntityTags(DataGenerator generatorIn, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, ForgeRegistries.ENTITIES, SnowyWeaponry.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(Reference.Tags.FIRE_MOBS)
            .add(EntityType.BLAZE);
    }

    @Override
    public String getName() {
        return "Entity Tags";
    }
}
