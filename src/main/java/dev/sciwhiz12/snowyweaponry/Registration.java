package dev.sciwhiz12.snowyweaponry;

import dev.sciwhiz12.snowyweaponry.Reference.Items;
import dev.sciwhiz12.snowyweaponry.item.PotionConeItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.block.DispenserBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public final class Registration {
    private Registration() {
    } // Prevent instantiation

    @SubscribeEvent
    static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(Registration::registerDispenserBehavior);
    }

    static void registerDispenserBehavior() {
        SnowyWeaponry.LOG.debug("Registering dispenser behavior for items");
        DispenserBlock.registerProjectileBehavior(Items.IRON_CORED_SNOWBALL);
        DispenserBlock.registerProjectileBehavior(Items.GOLD_CORED_SNOWBALL);
        DispenserBlock.registerProjectileBehavior(Items.DIAMOND_CORED_SNOWBALL);
        DispenserBlock.registerProjectileBehavior(Items.NETHERITE_CORED_SNOWBALL);
        DispenserBlock.registerProjectileBehavior(Items.EXPLOSIVE_SNOWBALL);
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

            BuiltInRegistries.POTION.listElements()
                    .filter(potion -> potion.value().isEnabled(event.getFlags()))
                    .map(potion -> PotionConeItem.createItemStack(1, potion))
                    .forEach(event::accept);
        }
    }
}
