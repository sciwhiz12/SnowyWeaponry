package sciwhiz12.snowyweaponry.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeRegistryTagsProvider;
import net.minecraftforge.registries.ForgeRegistries;
import sciwhiz12.snowyweaponry.Reference;
import sciwhiz12.snowyweaponry.SnowyWeaponry;

import javax.annotation.Nullable;

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
