package tk.sciwhiz12.snowyweaponry;

import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.tags.ITagManager;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import tk.sciwhiz12.snowyweaponry.damage.CoredSnowballDamageSource;
import tk.sciwhiz12.snowyweaponry.entity.CoredSnowball;
import tk.sciwhiz12.snowyweaponry.entity.ExplosiveSnowball;
import tk.sciwhiz12.snowyweaponry.item.CoredSnowballItem;
import tk.sciwhiz12.snowyweaponry.item.ExplosiveSnowballItem;
import tk.sciwhiz12.snowyweaponry.item.PotionConeItem;
import tk.sciwhiz12.snowyweaponry.recipe.PotionConeRecipe;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * Registers and holds references to constants and objects created and registered by this mod.
 *
 * @author SciWhiz12
 */
public final class Reference {
    private Reference() {
    } // Prevent instantiation

    @MonotonicNonNull
    public static CreativeModeTab ITEM_GROUP = null;

    public static final class Items {
        private Items() {
        } // Prevent instantiation

        static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, SnowyWeaponry.MODID);

        public static final RegistryObject<Item> DIAMOND_CHUNK = REGISTER.register("diamond_chunk", () ->
                new Item(itemProps()
                        .stacksTo(64)));
        public static final RegistryObject<Item> NETHERITE_NUGGET = REGISTER.register("netherite_nugget", () ->
                new Item(itemProps()
                        .stacksTo(64)));

        public static final RegistryObject<CoredSnowballItem> IRON_CORED_SNOWBALL = REGISTER.register("iron_cored_snowball", () ->
                new CoredSnowballItem(itemProps()
                        .stacksTo(16),
                        2, 0));
        public static final RegistryObject<CoredSnowballItem> GOLD_CORED_SNOWBALL = REGISTER.register("gold_cored_snowball", () ->
                new CoredSnowballItem(itemProps()
                        .stacksTo(16),
                        1, 1));
        public static final RegistryObject<CoredSnowballItem> DIAMOND_CORED_SNOWBALL = REGISTER.register("diamond_cored_snowball", () ->
                new CoredSnowballItem(itemProps()
                        .stacksTo(16),
                        3, 0, () ->
                        new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 0, false, true, false)));
        public static final RegistryObject<CoredSnowballItem> NETHERITE_CORED_SNOWBALL = REGISTER.register("netherite_cored_snowball", () ->
                new CoredSnowballItem(itemProps()
                        .stacksTo(16),
                        4, 0, () ->
                        new MobEffectInstance(MobEffects.BLINDNESS, 40, 0, false, true, false)));
        public static final RegistryObject<ExplosiveSnowballItem> EXPLOSIVE_SNOWBALL = REGISTER.register("explosive_snowball", () ->
                new ExplosiveSnowballItem(itemProps()
                        .stacksTo(8)));

        public static final RegistryObject<Item> WAFER_CONE = REGISTER.register("wafer_cone", () ->
                new Item(itemProps()
                        .stacksTo(32)
                        .food(new FoodProperties.Builder()
                                .fast()
                                .nutrition(1)
                                .saturationMod(0.1F)
                                .build())));
        public static final RegistryObject<Item> SNOW_CONE = REGISTER.register("snow_cone", () -> new Item(itemProps()
                .stacksTo(8)
                .food(new FoodProperties.Builder()
                        .fast()
                        .nutrition(2)
                        .saturationMod(0.2F)
                        .build())));
        public static final RegistryObject<Item> GOLDEN_SNOW_CONE = REGISTER.register("golden_snow_cone", () ->
                new Item(itemProps()
                        .stacksTo(8)
                        .food(new FoodProperties.Builder()
                                .fast()
                                .nutrition(4)
                                .saturationMod(1.0F)
                                .alwaysEat()
                                .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 120, 0, false, true), 1)
                                .build())));
        public static final RegistryObject<PotionConeItem> POTION_SNOW_CONE = REGISTER.register("potion_snow_cone", () ->
                new PotionConeItem(itemProps()
                        .stacksTo(8)
                        .food(new FoodProperties.Builder()
                                .fast()
                                .nutrition(2)
                                .saturationMod(0.3F)
                                .alwaysEat()
                                .build())));

        private static Item.Properties itemProps() {
            return new Item.Properties();
        }
    }

    public static final class EntityTypes {
        private EntityTypes() {
        } // Prevent instantiation

        static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SnowyWeaponry.MODID);

        public static final RegistryObject<EntityType<CoredSnowball>> CORED_SNOWBALL = register("cored_snowball", () ->
                EntityType.Builder.<CoredSnowball>of(CoredSnowball::new, MobCategory.MISC)
                        .sized(0.25F, 0.25F)
                        .clientTrackingRange(4)
                        .updateInterval(10));
        public static final RegistryObject<EntityType<ExplosiveSnowball>> EXPLOSIVE_SNOWBALL = register("explosive_snowball", () ->
                EntityType.Builder.<ExplosiveSnowball>of(ExplosiveSnowball::new, MobCategory.MISC)
                        .sized(0.25F, 0.25F)
                        .clientTrackingRange(4)
                        .updateInterval(10));

        private static <T extends Entity> RegistryObject<EntityType<T>> register(String name, Supplier<EntityType.Builder<T>> builder) {
            return REGISTER.register(name, () -> builder.get().build(SnowyWeaponry.loc(name).toString()));
        }
    }

    public static final class DamageSources {
        private DamageSources() {
        } // Prevent instantiation

        public static DamageSource causeSnowballDamage(Entity entity, @Nullable Entity owner, int lootingLevel) {
            return (new CoredSnowballDamageSource("snowball", entity, owner, lootingLevel)).setProjectile();
        }

        public static DamageSource causeSnowballExplosionDamage(@Nullable Entity entity) {
            return entity instanceof LivingEntity ?
                    (new EntityDamageSource("snowball.explosion.player", entity)).setScalesWithDifficulty().setExplosion() :
                    (new DamageSource("snowball.explosion")).setScalesWithDifficulty().setExplosion();
        }
    }

    public static final class RecipeSerializers {
        private RecipeSerializers() {
        } // Prevent instantiation

        static final DeferredRegister<RecipeSerializer<?>> REGISTER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SnowyWeaponry.MODID);

        public static final RegistryObject<SimpleCraftingRecipeSerializer<PotionConeRecipe>> POTION_CONE_RECIPE = REGISTER.register("potion_cone_recipe", () ->
                new SimpleCraftingRecipeSerializer<>(PotionConeRecipe::new));
    }

    public static final class Tags {
        private Tags() {
        } // Prevent instantiation

        public static final TagKey<EntityType<?>> FIRE_MOBS = tags(ForgeRegistries.ENTITY_TYPES)
                .createTagKey(SnowyWeaponry.loc("fire_mobs"));

        public static final TagKey<Item> NUGGETS_DIAMOND = tags(ForgeRegistries.ITEMS)
                .createTagKey(new ResourceLocation("forge", "nuggets/diamond"));
        public static final TagKey<Item> NUGGETS_NETHERITE = tags(ForgeRegistries.ITEMS)
                .createTagKey(new ResourceLocation("forge", "nuggets/netherite"));

        private static <T> ITagManager<T> tags(IForgeRegistry<T> registry) {
            return Objects.requireNonNull(registry.tags());
        }
    }

    public static final class DispenseBehaviors {
        private DispenseBehaviors() {
        } // Prevent instantiation

        public static final AbstractProjectileDispenseBehavior CORED_SNOWBALL = new AbstractProjectileDispenseBehavior() {
            @Override
            protected Projectile getProjectile(Level world, Position pos, ItemStack stack) {
                CoredSnowball entity = new CoredSnowball(world, pos.x(), pos.y(), pos.z());
                entity.setItem(stack);
                return entity;
            }
        };

        public static final AbstractProjectileDispenseBehavior EXPLOSIVE_SNOWBALL = new AbstractProjectileDispenseBehavior() {
            @Override
            protected Projectile getProjectile(Level world, Position pos, ItemStack stack) {
                ExplosiveSnowball entity = new ExplosiveSnowball(world, pos.x(), pos.y(), pos.z());
                entity.setItem(stack);
                return entity;
            }
        };
    }
}
