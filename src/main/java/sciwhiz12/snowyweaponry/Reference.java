package sciwhiz12.snowyweaponry;

import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.Level;
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
    private Reference() {
    } // Prevent instantiation

    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab("snowy_weapons") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.GOLD_CORED_SNOWBALL);
        }
    };

    @ObjectHolder(MODID)
    public static final class Items {
        private Items() {
        } // Prevent instantiation

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
        private EntityTypes() {
        } // Prevent instantiation

        public static final EntityType<CoredSnowballEntity> CORED_SNOWBALL = Null();
        public static final EntityType<ExplosiveSnowballEntity> EXPLOSIVE_SNOWBALL = Null();
    }

    @ObjectHolder(MODID)
    public static final class DamageSources {
        private DamageSources() {
        } // Prevent instantiation

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
        private RecipeSerializers() {
        } // Prevent instantiation

        public static final SimpleRecipeSerializer<PotionConeRecipe> POTION_CONE_RECIPE = Null();
    }

    public static final class Tags {
        private Tags() {
        } // Prevent instantiation

        public static final Tag.Named<EntityType<?>> FIRE_MOBS = EntityTypeTags.bind(MODID + ":fire_mobs");

        public static final IOptionalNamedTag<Item> NUGGETS_DIAMOND = ItemTags
            .createOptional(new ResourceLocation("forge", "nuggets/diamond"));
        public static final IOptionalNamedTag<Item> NUGGETS_NETHERITE = ItemTags
            .createOptional(new ResourceLocation("forge", "nuggets/netherite"));
    }

    public static final class DispenseBehaviors {
        private DispenseBehaviors() {
        } // Prevent instantiation

        public static final AbstractProjectileDispenseBehavior CORED_SNOWBALL = new AbstractProjectileDispenseBehavior() {
            @Override
            protected Projectile getProjectile(Level world, Position pos, ItemStack stack) {
                CoredSnowballEntity entity = new CoredSnowballEntity(world, pos.x(), pos.y(), pos.z());
                entity.setItem(stack);
                return entity;
            }
        };

        public static final AbstractProjectileDispenseBehavior EXPLOSIVE_SNOWBALL = new AbstractProjectileDispenseBehavior() {
            @Override
            protected Projectile getProjectile(Level world, Position pos, ItemStack stack) {
                ExplosiveSnowballEntity entity = new ExplosiveSnowballEntity(world, pos.x(), pos.y(), pos.z());
                entity.setItem(stack);
                return entity;
            }
        };
    }
}
