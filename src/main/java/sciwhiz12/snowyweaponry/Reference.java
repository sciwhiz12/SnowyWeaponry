package sciwhiz12.snowyweaponry;

import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags.IOptionalNamedTag;
import net.minecraftforge.registries.ObjectHolder;
import sciwhiz12.snowyweaponry.damage.CoredSnowballDamageSource;
import sciwhiz12.snowyweaponry.entity.CoredSnowballEntity;
import sciwhiz12.snowyweaponry.entity.ExplosiveSnowballEntity;
import sciwhiz12.snowyweaponry.item.CoredSnowballItem;
import sciwhiz12.snowyweaponry.item.ExplosiveSnowballItem;
import sciwhiz12.snowyweaponry.item.PotionConeItem;
import sciwhiz12.snowyweaponry.recipe.PotionConeRecipe;

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
        public ItemStack makeIcon() {
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
        public static final ExplosiveSnowballItem EXPLOSIVE_SNOWBALL = Null();

        public static final Item WAFER_CONE = Null();
        public static final Item SNOW_CONE = Null();
        public static final Item GOLDEN_SNOW_CONE = Null();
        public static final PotionConeItem POTION_SNOW_CONE = Null();
    }

    @ObjectHolder(MODID)
    public static final class EntityTypes {
        private EntityTypes() {} // Prevent instantiation

        public static final EntityType<CoredSnowballEntity> CORED_SNOWBALL = Null();
        public static final EntityType<ExplosiveSnowballEntity> EXPLOSIVE_SNOWBALL = Null();
    }

    @ObjectHolder(MODID)
    public static final class DamageSources {
        private DamageSources() {} // Prevent instantiation

        public static DamageSource causeSnowballDamage(Entity source, @Nullable Entity indirectSource, int lootingLevel) {
            return (new CoredSnowballDamageSource("snowball", source, indirectSource, lootingLevel)).setProjectile();
        }

        public static DamageSource causeSnowballExplosionDamage(@Nullable Entity entity) {
            return entity instanceof LivingEntity ?
                (new EntityDamageSource("snowball.explosion.player", entity)).setScalesWithDifficulty().setExplosion() :
                (new DamageSource("snowball.explosion")).setScalesWithDifficulty().setExplosion();
        }
    }

    @ObjectHolder(MODID)
    public static final class RecipeSerializers {
        private RecipeSerializers() {} // Prevent instantiation

        public static final SpecialRecipeSerializer<PotionConeRecipe> POTION_CONE_RECIPE = Null();
    }

    public static final class Tags {
        private Tags() {} // Prevent instantiation

        public static final ITag.INamedTag<EntityType<?>> FIRE_MOBS = EntityTypeTags.bind(MODID + ":fire_mobs");

        public static final IOptionalNamedTag<Item> NUGGETS_DIAMOND = ItemTags
            .createOptional(new ResourceLocation("forge", "nuggets/diamond"));
        public static final IOptionalNamedTag<Item> NUGGETS_NETHERITE = ItemTags
            .createOptional(new ResourceLocation("forge", "nuggets/netherite"));
    }

    public static final class DispenseBehaviors {
        private DispenseBehaviors() {} // Prevent instantiation

        public static final ProjectileDispenseBehavior CORED_SNOWBALL = new ProjectileDispenseBehavior() {
            @Override
            protected ProjectileEntity getProjectile(World world, IPosition pos, ItemStack stack) {
                CoredSnowballEntity entity = new CoredSnowballEntity(world, pos.x(), pos.y(), pos.z());
                entity.setItem(stack);
                return entity;
            }
        };

        public static final ProjectileDispenseBehavior EXPLOSIVE_SNOWBALL = new ProjectileDispenseBehavior() {
            @Override
            protected ProjectileEntity getProjectile(World world, IPosition pos, ItemStack stack) {
                ExplosiveSnowballEntity entity = new ExplosiveSnowballEntity(world, pos.x(), pos.y(), pos.z());
                entity.setItem(stack);
                return entity;
            }
        };
    }
}
