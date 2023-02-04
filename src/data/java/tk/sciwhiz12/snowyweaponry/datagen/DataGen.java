package tk.sciwhiz12.snowyweaponry.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

import static tk.sciwhiz12.snowyweaponry.SnowyWeaponry.LOG;
import static tk.sciwhiz12.snowyweaponry.SnowyWeaponry.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {
    @SubscribeEvent
    static void onGatherData(GatherDataEvent event) {
        LOG.debug("Gathering data for data generation");
        final DataGenerator gen = event.getGenerator();
        final PackOutput output = event.getGenerator().getPackOutput();
        final CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        final ExistingFileHelper helper = event.getExistingFileHelper();

        gen.addProvider(event.includeClient(), new Languages(output));
        gen.addProvider(event.includeClient(), new ItemModels(output, helper));

        gen.addProvider(event.includeServer(), new Recipes(output));
        final BlockTagsProvider blockTags = new BlockTags(output, lookupProvider, helper);
        gen.addProvider(event.includeServer(), blockTags);
        gen.addProvider(event.includeServer(), new ItemTags(output, lookupProvider, blockTags, helper));
        gen.addProvider(event.includeServer(), new EntityTags(output, lookupProvider, helper));
    }
}
