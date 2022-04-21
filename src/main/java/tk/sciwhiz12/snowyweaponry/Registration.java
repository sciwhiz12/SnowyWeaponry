package tk.sciwhiz12.snowyweaponry;

import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

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
        DispenserBlock.registerBehavior(Reference.Items.IRON_CORED_SNOWBALL.get(), Reference.DispenseBehaviors.CORED_SNOWBALL);
        DispenserBlock.registerBehavior(Reference.Items.GOLD_CORED_SNOWBALL.get(), Reference.DispenseBehaviors.CORED_SNOWBALL);
        DispenserBlock.registerBehavior(Reference.Items.DIAMOND_CORED_SNOWBALL.get(), Reference.DispenseBehaviors.CORED_SNOWBALL);
        DispenserBlock.registerBehavior(Reference.Items.NETHERITE_CORED_SNOWBALL.get(), Reference.DispenseBehaviors.CORED_SNOWBALL);
        DispenserBlock.registerBehavior(Reference.Items.EXPLOSIVE_SNOWBALL.get(), Reference.DispenseBehaviors.EXPLOSIVE_SNOWBALL);
    }
}
