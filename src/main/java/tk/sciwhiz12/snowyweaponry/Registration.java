package tk.sciwhiz12.snowyweaponry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import tk.sciwhiz12.snowyweaponry.entity.CoredSnowball;
import tk.sciwhiz12.snowyweaponry.entity.ExplosiveSnowball;
import tk.sciwhiz12.snowyweaponry.item.CoredSnowballItem;
import tk.sciwhiz12.snowyweaponry.item.ExplosiveSnowballItem;
import tk.sciwhiz12.snowyweaponry.item.PotionConeItem;
import tk.sciwhiz12.snowyweaponry.recipe.PotionConeRecipe;

/**
 * Main class for registering objects of this mod.
 *
 * @author SciWhiz12
 */
@EventBusSubscriber(modid = SnowyWeaponry.MODID, bus = Bus.MOD)
public final class Registration {
    private Registration() {
    } // Prevent instantiation

    @SubscribeEvent
    static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(Registration::registerDispenserBehavior);
    }

    static void registerDispenserBehavior() {
        SnowyWeaponry.LOG.debug(SnowyWeaponry.COMMON, "Registering dispenser behavior for items");
        DispenserBlock.registerBehavior(Reference.Items.IRON_CORED_SNOWBALL, Reference.DispenseBehaviors.CORED_SNOWBALL);
        DispenserBlock.registerBehavior(Reference.Items.GOLD_CORED_SNOWBALL, Reference.DispenseBehaviors.CORED_SNOWBALL);
        DispenserBlock.registerBehavior(Reference.Items.DIAMOND_CORED_SNOWBALL, Reference.DispenseBehaviors.CORED_SNOWBALL);
        DispenserBlock.registerBehavior(Reference.Items.NETHERITE_CORED_SNOWBALL, Reference.DispenseBehaviors.CORED_SNOWBALL);
        DispenserBlock.registerBehavior(Reference.Items.EXPLOSIVE_SNOWBALL, Reference.DispenseBehaviors.EXPLOSIVE_SNOWBALL);
    }

    @SubscribeEvent
    static void onRegisterItems(RegistryEvent.Register<Item> event) {
        SnowyWeaponry.LOG.debug(SnowyWeaponry.COMMON, "Registering items");
        event.getRegistry().registerAll(
                new Item(itemProps().stacksTo(64).tab(CreativeModeTab.TAB_MISC)).setRegistryName("diamond_chunk"),
                new Item(itemProps().stacksTo(64).tab(CreativeModeTab.TAB_MISC)).setRegistryName("netherite_nugget"),

                new CoredSnowballItem(itemProps().stacksTo(16).tab(Reference.ITEM_GROUP), 2, 0,
                        null).setRegistryName("iron_cored_snowball"),
                new CoredSnowballItem(itemProps().stacksTo(16).tab(Reference.ITEM_GROUP), 1, 1,
                        null).setRegistryName("gold_cored_snowball"),
                new CoredSnowballItem(itemProps().stacksTo(16).tab(Reference.ITEM_GROUP), 3, 0,
                        () -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 0, false, true, false))
                        .setRegistryName("diamond_cored_snowball"),
                new CoredSnowballItem(itemProps().stacksTo(16).tab(Reference.ITEM_GROUP), 4, 0,
                        () -> new MobEffectInstance(MobEffects.BLINDNESS, 40, 0, false, true, false))
                        .setRegistryName("netherite_cored_snowball"),
                new ExplosiveSnowballItem(itemProps().stacksTo(8).tab(Reference.ITEM_GROUP))
                        .setRegistryName("explosive_snowball"),

                new Item(itemProps().stacksTo(32).tab(Reference.ITEM_GROUP)
                        .food(new FoodProperties.Builder().fast().nutrition(1).saturationMod(0.1F).build()))
                        .setRegistryName("wafer_cone"),
                new Item(itemProps().stacksTo(8).tab(Reference.ITEM_GROUP)
                        .food(new FoodProperties.Builder().fast().nutrition(2).saturationMod(0.2F).build()))
                        .setRegistryName("snow_cone"),
                new Item(itemProps().stacksTo(8).tab(Reference.ITEM_GROUP)
                        .food(new FoodProperties.Builder().fast().nutrition(4).saturationMod(1.0F).alwaysEat()
                                .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 120, 0, false, true), 1).build()))
                        .setRegistryName("golden_snow_cone"),
                new PotionConeItem(itemProps().stacksTo(8).tab(Reference.ITEM_GROUP)
                        .food(new FoodProperties.Builder().fast().nutrition(2).saturationMod(0.3F).alwaysEat().build()))
                        .setRegistryName("potion_snow_cone")
        );
    }

    @SubscribeEvent
    static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
        SnowyWeaponry.LOG.debug(SnowyWeaponry.COMMON, "Registering entity types");
        event.getRegistry().registerAll(
                build(EntityType.Builder.<CoredSnowball>of(CoredSnowball::new, MobCategory.MISC)
                                .sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10),
                        "cored_snowball"),
                build(EntityType.Builder.<ExplosiveSnowball>of(ExplosiveSnowball::new, MobCategory.MISC)
                                .sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10),
                        "explosive_snowball")
        );
    }

    @SubscribeEvent
    static void onRegisterRecipeSerializers(RegistryEvent.Register<RecipeSerializer<?>> event) {
        SnowyWeaponry.LOG.debug(SnowyWeaponry.COMMON, "Registering recipe serializers");
        event.getRegistry().registerAll(
                new SimpleRecipeSerializer<>(PotionConeRecipe::new).setRegistryName("potion_cone_recipe")
        );
    }

    private static <T extends Entity> EntityType<T> build(EntityType.Builder<T> builder, String entityName) {
        ResourceLocation name = new ResourceLocation(SnowyWeaponry.MODID, entityName);
        EntityType<T> type = builder.build(name.toString());
        type.setRegistryName(name);
        return type;
    }

    private static Item.Properties itemProps() {
        return new Item.Properties();
    }
}
