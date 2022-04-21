package tk.sciwhiz12.snowyweaponry.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

import static tk.sciwhiz12.snowyweaponry.SnowyWeaponry.LOG;
import static tk.sciwhiz12.snowyweaponry.SnowyWeaponry.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {
    @SubscribeEvent
    static void onGatherData(GatherDataEvent event) {
        LOG.debug("Gathering data for data generation");
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        if (event.includeClient()) {
            LOG.debug("Adding data providers for client assets");
            gen.addProvider(new Languages(gen));
            gen.addProvider(new ItemModels(gen, helper));
        }
        if (event.includeServer()) {
            LOG.debug("Adding data providers for server data");
            gen.addProvider(new Recipes(gen));
            gen.addProvider(new ItemTags(gen, helper));
            gen.addProvider(new EntityTags(gen, helper));
        }
    }
}
