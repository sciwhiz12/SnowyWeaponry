package tk.sciwhiz12.snowyweaponry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.DispenserBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import tk.sciwhiz12.snowyweaponry.Reference.Items;

public final class Registration {
    private Registration() {
    } // Prevent instantiation

    @SubscribeEvent
    static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(Registration::registerDispenserBehavior);
    }

    static void registerDispenserBehavior() {
        SnowyWeaponry.LOG.debug("Registering dispenser behavior for items");
        DispenserBlock.registerBehavior(Items.IRON_CORED_SNOWBALL, Reference.DispenseBehaviors.CORED_SNOWBALL);
        DispenserBlock.registerBehavior(Items.GOLD_CORED_SNOWBALL, Reference.DispenseBehaviors.CORED_SNOWBALL);
        DispenserBlock.registerBehavior(Items.DIAMOND_CORED_SNOWBALL, Reference.DispenseBehaviors.CORED_SNOWBALL);
        DispenserBlock.registerBehavior(Items.NETHERITE_CORED_SNOWBALL, Reference.DispenseBehaviors.CORED_SNOWBALL);
        DispenserBlock.registerBehavior(Items.EXPLOSIVE_SNOWBALL, Reference.DispenseBehaviors.EXPLOSIVE_SNOWBALL);
    }

    @SubscribeEvent
    static void onCreativeModeTabBuildContentsEvent(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(Reference.ITEM_TAB.unwrapKey().orElseThrow())) {
            event.accept(Items.DIAMOND_CHUNK);
            event.accept(Items.NETHERITE_NUGGET);

            event.accept(Items.IRON_CORED_SNOWBALL);
            event.accept(Items.GOLD_CORED_SNOWBALL);
            event.accept(Items.DIAMOND_CORED_SNOWBALL);
            event.accept(Items.NETHERITE_CORED_SNOWBALL);
            event.accept(Items.EXPLOSIVE_SNOWBALL);

            event.accept(Items.WAFER_CONE);
            event.accept(Items.SNOW_CONE);
            event.accept(Items.GOLDEN_SNOW_CONE);

            for (Potion potion : BuiltInRegistries.POTION) {
                if (potion != Potions.EMPTY) {
                    event.accept(PotionUtils.setPotion(new ItemStack(Items.POTION_SNOW_CONE.get()), potion));
                }
            }
        }
    }
}
