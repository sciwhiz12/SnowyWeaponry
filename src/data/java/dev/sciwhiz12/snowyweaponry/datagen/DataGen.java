package dev.sciwhiz12.snowyweaponry.datagen;

import dev.sciwhiz12.snowyweaponry.Reference;
import dev.sciwhiz12.snowyweaponry.SnowyWeaponry;
import net.minecraft.SharedConstants;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.RegistrySetBuilder.PatchedRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.data.registries.RegistryPatchGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.util.InclusiveRange;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = SnowyWeaponry.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGen {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        SnowyWeaponry.LOG.debug("Gathering data for data generation");
        final DataGenerator gen = event.getGenerator();
        final PackOutput output = event.getGenerator().getPackOutput();
        final CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        final ExistingFileHelper helper = event.getExistingFileHelper();

        gen.addProvider(event.includeClient(), new Languages(output));
        gen.addProvider(event.includeClient(), new ItemModels(output, helper));

        gen.addProvider(event.includeServer(), new Recipes(output, lookupProvider));
        final BlockTagsProvider blockTags = new BlockTags(output, lookupProvider, helper);
        gen.addProvider(event.includeServer(), blockTags);
        gen.addProvider(event.includeServer(), new ItemTags(output, lookupProvider, blockTags.contentsGetter(), helper));
        gen.addProvider(event.includeServer(), new EntityTags(output, lookupProvider, helper));

        final RegistrySetBuilder builder = new RegistrySetBuilder()
                .add(Registries.DAMAGE_TYPE, bootstrap -> {
                    bootstrap.register(Reference.DamageTypes.CORED_SNOWBALL,
                            new DamageType("snowball", 0.1F));
                    bootstrap.register(Reference.DamageTypes.CORED_SNOWBALL_EXPLOSION,
                            new DamageType("snowball.explosion", DamageScaling.ALWAYS, 0.1F));
                });
        gen.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(output, lookupProvider, builder, Set.of(SnowyWeaponry.MODID)));
        gen.addProvider(event.includeServer(), new DamageTypes(output, RegistryPatchGenerator.createLookup(lookupProvider, builder).thenApply(PatchedRegistries::patches), helper));
    }
}
