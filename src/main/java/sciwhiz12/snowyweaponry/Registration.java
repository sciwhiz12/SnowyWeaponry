package sciwhiz12.snowyweaponry;

import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import sciwhiz12.snowyweaponry.entity.CoredSnowballEntity;
import sciwhiz12.snowyweaponry.entity.ExplosiveSnowballEntity;
import sciwhiz12.snowyweaponry.item.CoredSnowballItem;
import sciwhiz12.snowyweaponry.item.ExplosiveSnowballItem;
import sciwhiz12.snowyweaponry.item.PotionConeItem;
import sciwhiz12.snowyweaponry.recipe.PotionConeRecipe;

import static sciwhiz12.snowyweaponry.Reference.*;
import static sciwhiz12.snowyweaponry.SnowyWeaponry.COMMON;
import static sciwhiz12.snowyweaponry.SnowyWeaponry.LOG;

/**
 * Main class for registering objects of this mod.
 *
 * @author SciWhiz12
 */
@EventBusSubscriber(modid = SnowyWeaponry.MODID, bus = Bus.MOD)
public final class Registration {
    private Registration() {} // Prevent instantiation

    @SubscribeEvent
    static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(Registration::registerDispenserBehavior);
    }

    static void registerDispenserBehavior() {
        LOG.debug(COMMON, "Registering dispenser behavior for items");
        DispenserBlock.registerDispenseBehavior(Items.IRON_CORED_SNOWBALL, DispenseBehaviors.CORED_SNOWBALL);
        DispenserBlock.registerDispenseBehavior(Items.GOLD_CORED_SNOWBALL, DispenseBehaviors.CORED_SNOWBALL);
        DispenserBlock.registerDispenseBehavior(Items.DIAMOND_CORED_SNOWBALL, DispenseBehaviors.CORED_SNOWBALL);
        DispenserBlock.registerDispenseBehavior(Items.NETHERITE_CORED_SNOWBALL, DispenseBehaviors.CORED_SNOWBALL);
        DispenserBlock.registerDispenseBehavior(Items.EXPLOSIVE_SNOWBALL, DispenseBehaviors.EXPLOSIVE_SNOWBALL);
    }

    @SubscribeEvent
    static void onRegisterItems(RegistryEvent.Register<Item> event) {
        LOG.debug(COMMON, "Registering items");
        event.getRegistry().registerAll(
            new Item(itemProps().maxStackSize(64).group(ItemGroup.MISC)).setRegistryName("diamond_chunk"),
            new Item(itemProps().maxStackSize(64).group(ItemGroup.MISC)).setRegistryName("netherite_nugget"),

            new CoredSnowballItem(itemProps().maxStackSize(16).group(ITEM_GROUP), 2, 0,
                null).setRegistryName("iron_cored_snowball"),
            new CoredSnowballItem(itemProps().maxStackSize(16).group(ITEM_GROUP), 1, 1,
                null).setRegistryName("gold_cored_snowball"),
            new CoredSnowballItem(itemProps().maxStackSize(16).group(ITEM_GROUP), 3, 0,
                () -> new EffectInstance(Effects.SLOWNESS, 30, 0, false, true, false))
                .setRegistryName("diamond_cored_snowball"),
            new CoredSnowballItem(itemProps().maxStackSize(16).group(ITEM_GROUP), 4, 0,
                () -> new EffectInstance(Effects.BLINDNESS, 40, 0, false, true, false))
                .setRegistryName("netherite_cored_snowball"),
            new ExplosiveSnowballItem(itemProps().maxStackSize(8).group(ITEM_GROUP))
                .setRegistryName("explosive_snowball"),

            new Item(itemProps().maxStackSize(32).group(ITEM_GROUP)
                .food(new Food.Builder().fastToEat().hunger(1).saturation(0.1F).build()))
                .setRegistryName("wafer_cone"),
            new Item(itemProps().maxStackSize(8).group(ITEM_GROUP)
                .food(new Food.Builder().fastToEat().hunger(2).saturation(0.2F).build()))
                .setRegistryName("snow_cone"),
            new Item(itemProps().maxStackSize(8).group(ITEM_GROUP)
                .food(new Food.Builder().fastToEat().hunger(4).saturation(1.0F).setAlwaysEdible()
                    .effect(() -> new EffectInstance(Effects.FIRE_RESISTANCE, 120, 0, false, true), 1).build()))
                .setRegistryName("golden_snow_cone"),
            new PotionConeItem(itemProps().maxStackSize(8).group(ITEM_GROUP)
                .food(new Food.Builder().fastToEat().hunger(2).saturation(0.3F).setAlwaysEdible().build()))
                .setRegistryName("potion_snow_cone")
        );
    }

    @SubscribeEvent
    static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
        LOG.debug(COMMON, "Registering entity types");
        event.getRegistry().registerAll(
            build(EntityType.Builder.<CoredSnowballEntity>create(CoredSnowballEntity::new, EntityClassification.MISC)
                    .size(0.25F, 0.25F).trackingRange(4).func_233608_b_(10),
                "cored_snowball"),
            build(EntityType.Builder.<ExplosiveSnowballEntity>create(ExplosiveSnowballEntity::new, EntityClassification.MISC)
                    .size(0.25F, 0.25F).trackingRange(4).func_233608_b_(10),
                "explosive_snowball")
        );
    }

    @SubscribeEvent
    static void onRegisterRecipeSerializers(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        LOG.debug(COMMON, "Registering recipe serializers");
        event.getRegistry().registerAll(
            new SpecialRecipeSerializer<>(PotionConeRecipe::new).setRegistryName("potion_cone_recipe")
        );
    }

    private static <T extends Entity> EntityType<T> build(EntityType.Builder<T> builder, String entityName) {
        ResourceLocation name = new ResourceLocation(SnowyWeaponry.MODID, entityName);
        EntityType<T> type = builder.build(name.toString());
        type.setRegistryName(name);
        return type;
    }

    private static Item.Properties itemProps() { return new Item.Properties(); }
}
