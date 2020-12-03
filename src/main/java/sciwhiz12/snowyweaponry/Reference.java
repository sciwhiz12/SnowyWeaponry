package sciwhiz12.snowyweaponry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags.IOptionalNamedTag;
import net.minecraftforge.registries.ObjectHolder;
import sciwhiz12.snowyweaponry.damage.CoredSnowballDamageSource;
import sciwhiz12.snowyweaponry.entity.CoredSnowballEntity;
import sciwhiz12.snowyweaponry.item.CoredSnowballItem;

import javax.annotation.Nullable;

import static sciwhiz12.snowyweaponry.SnowyWeaponry.MODID;
import static sciwhiz12.snowyweaponry.util.Util.Null;

/**
 * Holds references to constants and objects created and registered by this mod.
 *
 * @author SciWhiz12
 */
public final class Reference {
    private Reference() {} // Prevent instantiation

    public static final ItemGroup ITEM_GROUP = new ItemGroup("snowy_weapons") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.GOLD_CORED_SNOWBALL);
        }
    };

    @ObjectHolder(MODID)
    public static final class Items {
        private Items() {} // Prevent instantiation

        public static final Item DIAMOND_CHUNK = Null();
        public static final Item NETHERITE_NUGGET = Null();

        public static final CoredSnowballItem IRON_CORED_SNOWBALL = Null();
        public static final CoredSnowballItem GOLD_CORED_SNOWBALL = Null();
        public static final CoredSnowballItem DIAMOND_CORED_SNOWBALL = Null();
        public static final CoredSnowballItem NETHERITE_CORED_SNOWBALL = Null();

        public static final Item WAFER_CONE = Null();
        public static final Item SNOW_CONE = Null();
        public static final Item GOLDEN_SNOW_CONE = Null();
    }

    @ObjectHolder(MODID)
    public static final class EntityTypes {
        private EntityTypes() {} // Prevent instantiation

        public static final EntityType<CoredSnowballEntity> CORED_SNOWBALL = Null();
    }

    @ObjectHolder(MODID)
    public static final class DamageSources {
        private DamageSources() {} // Prevent instantiation

        public static DamageSource causeSnowballDamage(Entity source, @Nullable Entity indirectSource, int lootingLevel) {
            return (new CoredSnowballDamageSource("snowball", source, indirectSource, lootingLevel)).setProjectile();
        }
    }

    public static final class Tags {
        private Tags() {} // Prevent instantiation

        public static final ITag.INamedTag<EntityType<?>> FIRE_MOBS = EntityTypeTags.getTagById(MODID + ":fire_mobs");

        public static final IOptionalNamedTag<Item> NUGGETS_DIAMOND = ItemTags
            .createOptional(new ResourceLocation("forge", "nuggets/diamond"));
        public static final IOptionalNamedTag<Item> NUGGETS_NETHERITE = ItemTags
            .createOptional(new ResourceLocation("forge", "nuggets/netherite"));
    }
}
