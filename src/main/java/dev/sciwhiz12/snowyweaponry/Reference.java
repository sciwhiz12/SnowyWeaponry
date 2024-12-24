package dev.sciwhiz12.snowyweaponry;

import dev.sciwhiz12.snowyweaponry.entity.CoredSnowball;
import dev.sciwhiz12.snowyweaponry.entity.ExplosiveSnowball;
import dev.sciwhiz12.snowyweaponry.item.CoredSnowballItem;
import dev.sciwhiz12.snowyweaponry.item.ExplosiveSnowballItem;
import dev.sciwhiz12.snowyweaponry.item.PotionConeItem;
import dev.sciwhiz12.snowyweaponry.recipe.PotionConeRecipe;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/**
 * Registers and holds references to constants and objects created and registered by this mod.
 *
 * @author SciWhiz12
 */
public final class Reference {
    private Reference() {
    } // Prevent instantiation

    static final DeferredRegister<CreativeModeTab> TAB_REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SnowyWeaponry.MODID);

    public static final Holder<CreativeModeTab> ITEM_TAB = TAB_REGISTER.register("items", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.snowy_weapons"))
            .icon(() -> new ItemStack(Items.GOLD_CORED_SNOWBALL.get()))
            .build());

    public static final class Items {
        private Items() {
        } // Prevent instantiation

        static final DeferredRegister.Items REGISTER = DeferredRegister.createItems(SnowyWeaponry.MODID);

        public static final DeferredItem<Item> DIAMOND_CHUNK = REGISTER.registerItem("diamond_chunk", props ->
                new Item(props
                        .stacksTo(64)));
        public static final DeferredItem<Item> NETHERITE_NUGGET = REGISTER.registerItem("netherite_nugget", props ->
                new Item(props
                        .stacksTo(64)));

        public static final DeferredItem<CoredSnowballItem> IRON_CORED_SNOWBALL = REGISTER.registerItem("iron_cored_snowball", props ->
                new CoredSnowballItem(props
                        .stacksTo(16),
                        2, 0));
        public static final DeferredItem<CoredSnowballItem> GOLD_CORED_SNOWBALL = REGISTER.registerItem("gold_cored_snowball", props ->
                new CoredSnowballItem(props
                        .stacksTo(16),
                        1, 1));
        public static final DeferredItem<CoredSnowballItem> DIAMOND_CORED_SNOWBALL = REGISTER.registerItem("diamond_cored_snowball", props ->
                new CoredSnowballItem(props
                        .stacksTo(16),
                        3, 0, () ->
                        new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 0, false, true, false)));
        public static final DeferredItem<CoredSnowballItem> NETHERITE_CORED_SNOWBALL = REGISTER.registerItem("netherite_cored_snowball", props ->
                new CoredSnowballItem(props
                        .stacksTo(16),
                        4, 0, () ->
                        new MobEffectInstance(MobEffects.BLINDNESS, 40, 0, false, true, false)));
        public static final DeferredItem<ExplosiveSnowballItem> EXPLOSIVE_SNOWBALL = REGISTER.registerItem("explosive_snowball", props ->
                new ExplosiveSnowballItem(props
                        .stacksTo(8)));

        public static final DeferredItem<Item> WAFER_CONE = REGISTER.registerItem("wafer_cone", props ->
                new Item(props
                        .stacksTo(32)
                        .food(new FoodProperties.Builder()
                                        .nutrition(1)
                                        .saturationModifier(0.1F)
                                        .build(),
                                Consumables.defaultFood()
                                        .consumeSeconds(Consumable.DEFAULT_CONSUME_SECONDS / 2)
                                        .build()
                        )));
        public static final DeferredItem<Item> SNOW_CONE = REGISTER.registerItem("snow_cone", props ->
                new Item(props
                        .stacksTo(8)
                        .food(new FoodProperties.Builder()
                                        .nutrition(2)
                                        .saturationModifier(0.2F)
                                        .build(),
                                Consumables.defaultFood()
                                        .consumeSeconds(Consumable.DEFAULT_CONSUME_SECONDS / 2)
                                        .build()
                        )));
        public static final DeferredItem<Item> GOLDEN_SNOW_CONE = REGISTER.registerItem("golden_snow_cone", props ->
                new Item(props
                        .stacksTo(8)
                        .food(new FoodProperties.Builder()
                                        .nutrition(4)
                                        .saturationModifier(1.0F)
                                        .alwaysEdible()
                                        .build(),
                                Consumables.defaultFood()
                                        .consumeSeconds(Consumable.DEFAULT_CONSUME_SECONDS / 2)
                                        .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 120, 0, false, true)))
                                        .build()
                        )));
        public static final DeferredItem<PotionConeItem> POTION_SNOW_CONE = REGISTER.registerItem("potion_snow_cone", props ->
                new PotionConeItem(props
                        .stacksTo(8)
                        .food(new FoodProperties.Builder()
                                        .nutrition(2)
                                        .saturationModifier(0.3F)
                                        .alwaysEdible()
                                        .build(),
                                Consumables.defaultFood()
                                        .consumeSeconds(Consumable.DEFAULT_CONSUME_SECONDS / 2)
                                        .build()
                        )));
    }

    public static final class EntityTypes {
        private EntityTypes() {
        } // Prevent instantiation

        static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(Registries.ENTITY_TYPE, SnowyWeaponry.MODID);

        public static final DeferredHolder<EntityType<?>, EntityType<CoredSnowball>> CORED_SNOWBALL = register("cored_snowball", () ->
                EntityType.Builder.<CoredSnowball>of(CoredSnowball::new, MobCategory.MISC)
                        .sized(0.25F, 0.25F)
                        .clientTrackingRange(4)
                        .updateInterval(10));
        public static final DeferredHolder<EntityType<?>, EntityType<ExplosiveSnowball>> EXPLOSIVE_SNOWBALL = register("explosive_snowball", () ->
                EntityType.Builder.<ExplosiveSnowball>of(ExplosiveSnowball::new, MobCategory.MISC)
                        .sized(0.25F, 0.25F)
                        .clientTrackingRange(4)
                        .updateInterval(10));

        private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String name, Supplier<EntityType.Builder<T>> builder) {
            return REGISTER.register(name, () -> builder.get().build(ResourceKey.create(Registries.ENTITY_TYPE, SnowyWeaponry.loc(name))));
        }
    }

    public static final class DamageTypes {
        private DamageTypes() {
        } // Prevent instantiation

        public static final ResourceKey<DamageType> CORED_SNOWBALL = ResourceKey.create(Registries.DAMAGE_TYPE,
                SnowyWeaponry.loc("cored_snowball"));

        public static final ResourceKey<DamageType> CORED_SNOWBALL_EXPLOSION = ResourceKey.create(Registries.DAMAGE_TYPE,
                SnowyWeaponry.loc("cored_snowball_explosion"));
    }

    public static final class RecipeSerializers {
        private RecipeSerializers() {
        } // Prevent instantiation

        static final DeferredRegister<RecipeSerializer<?>> REGISTER = DeferredRegister.create(Registries.RECIPE_SERIALIZER, SnowyWeaponry.MODID);

        public static final DeferredHolder<RecipeSerializer<?>, CustomRecipe.Serializer<PotionConeRecipe>> POTION_CONE_RECIPE =
                REGISTER.register("potion_cone_recipe", () ->
                        new CustomRecipe.Serializer<>(PotionConeRecipe::new));
    }

    public static final class Tags {
        private Tags() {
        } // Prevent instantiation

        public static final TagKey<EntityType<?>> FIRE_MOBS = TagKey.create(Registries.ENTITY_TYPE, SnowyWeaponry.loc("fire_mobs"));

        public static final TagKey<Item> NUGGETS_DIAMOND = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "nuggets/diamond"));
        public static final TagKey<Item> NUGGETS_NETHERITE = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "nuggets/netherite"));
    }
}
