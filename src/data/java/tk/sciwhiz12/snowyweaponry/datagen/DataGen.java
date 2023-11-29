package tk.sciwhiz12.snowyweaponry.datagen;

import net.minecraft.SharedConstants;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.util.InclusiveRange;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import tk.sciwhiz12.snowyweaponry.Reference;

import java.util.Optional;
import java.util.Set;
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

        gen.addProvider(event.includeClient() || event.includeServer(), new PackMetadataGenerator(output)
                .add(PackMetadataSection.TYPE, new PackMetadataSection(
                        Component.literal("Snowy Weaponry resources"),
                        SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES),
                        Optional.of(createRange(
                                SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES),
                                SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA)
                        ))
                        // TODO: when on NeoForge, switch to pack-type-specific versions
                        // Maps.asMap(Set.of(PackType.values()), SharedConstants.getCurrentVersion()::getPackVersion)
                )));

        gen.addProvider(event.includeClient(), new Languages(output));
        gen.addProvider(event.includeClient(), new ItemModels(output, helper));

        gen.addProvider(event.includeServer(), new Recipes(output));
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
        gen.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(output, lookupProvider, builder, Set.of(MODID)));
        gen.addProvider(event.includeServer(), new DamageTypes(output, lookupProvider.thenApply(o -> append(o, builder)), helper));
    }

    private static InclusiveRange<Integer> createRange(int a, int b) {
        return new InclusiveRange<>(Math.min(a, b), Math.max(a, b));
    }

    private static HolderLookup.Provider append(HolderLookup.Provider original, RegistrySetBuilder builder) {
        return builder.buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), original);
    }
}
