package dev.sciwhiz12.snowyweaponry.datagen;

import dev.sciwhiz12.snowyweaponry.Reference;
import dev.sciwhiz12.snowyweaponry.SnowyWeaponry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.RegistrySetBuilder.PatchedRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.RegistryPatchGenerator;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = SnowyWeaponry.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGen {
    @SubscribeEvent
    public static void onGatherDataClient(GatherDataEvent.Client event) {
        SnowyWeaponry.LOG.debug("Gathering data for client data generation");
        final PackOutput output = event.getGenerator().getPackOutput();

        event.addProvider(new Languages(output));
        event.addProvider(new Models(output));
    }

    @SubscribeEvent
    public static void onGatherDataServer(GatherDataEvent.Server event) {
        SnowyWeaponry.LOG.debug("Gathering data for server data generation");
        final PackOutput output = event.getGenerator().getPackOutput();
        final CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        //noinspection removal
        final ExistingFileHelper helper = event.getExistingFileHelper();

        event.addProvider(new Recipes.Runner(output, lookupProvider));
        final BlockTagsProvider blockTags = new BlockTags(output, lookupProvider, helper);
        event.addProvider(blockTags);
        event.addProvider(new ItemTags(output, lookupProvider, blockTags.contentsGetter(), helper));
        event.addProvider(new EntityTags(output, lookupProvider, helper));

        final RegistrySetBuilder builder = new RegistrySetBuilder()
                .add(Registries.DAMAGE_TYPE, bootstrap -> {
                    bootstrap.register(Reference.DamageTypes.CORED_SNOWBALL,
                            new DamageType("snowball", 0.1F));
                    bootstrap.register(Reference.DamageTypes.CORED_SNOWBALL_EXPLOSION,
                            new DamageType("snowball.explosion", DamageScaling.ALWAYS, 0.1F));
                });
        event.addProvider(new DatapackBuiltinEntriesProvider(output, lookupProvider, builder, Set.of(SnowyWeaponry.MODID)));
        event.addProvider(new DamageTypes(output, RegistryPatchGenerator.createLookup(lookupProvider, builder).thenApply(PatchedRegistries::patches), helper));
    }
}
