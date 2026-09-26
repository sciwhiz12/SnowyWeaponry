package dev.sciwhiz12.snowyweaponry.datagen;

import dev.sciwhiz12.snowyweaponry.Reference;
import dev.sciwhiz12.snowyweaponry.SnowyWeaponry;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;

@EventBusSubscriber(modid = SnowyWeaponry.MODID)
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

        event.createReloadableRegistryObjects(new RegistrySetBuilder()
                        .add(RecipeProvider.asBootstrap(Recipes::new)),
                Set.of(SnowyWeaponry.MODID));
        event.createWorldRegistryObjects(new RegistrySetBuilder()
                        .add(Registries.DAMAGE_TYPE, bootstrap -> {
                            bootstrap.register(Reference.DamageTypes.CORED_SNOWBALL,
                                    new DamageType("snowball", 0.1F));
                            bootstrap.register(Reference.DamageTypes.CORED_SNOWBALL_EXPLOSION,
                                    new DamageType("snowball.explosion", DamageScaling.ALWAYS, 0.1F));
                        }),
                Set.of(SnowyWeaponry.MODID));

        event.createProvider(ItemTags::new);
        event.createProvider(EntityTags::new);
        event.addProvider(new DamageTypeTags(event.getGenerator().getPackOutput(), event.getWorldLookupProvider()));
    }
}
