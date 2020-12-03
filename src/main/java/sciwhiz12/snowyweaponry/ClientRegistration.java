package sciwhiz12.snowyweaponry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.function.Supplier;

import static sciwhiz12.snowyweaponry.Reference.EntityTypes.CORED_SNOWBALL;
import static sciwhiz12.snowyweaponry.SnowyWeaponry.CLIENT;
import static sciwhiz12.snowyweaponry.SnowyWeaponry.LOG;

/**
 * Class for registering <strong>client-side only</strong> objects of this mod.
 *
 * @author SciWhiz12
 */
@EventBusSubscriber(modid = SnowyWeaponry.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public final class ClientRegistration {
    private ClientRegistration() {} // Prevent instantiation

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        LOG.debug(CLIENT, "Setting up on client");
        registerEntityRenderers(event.getMinecraftSupplier());
    }

    @SubscribeEvent
    static void onColorHandlerItem(ColorHandlerEvent.Item event) {
        LOG.debug(CLIENT, "Registering item colors");
        event.getItemColors().register(
            (stack, index) -> index != 1 ? -1 : PotionUtils.getColor(stack),
            Reference.Items.POTION_SNOW_CONE
        );
    }

    static void registerEntityRenderers(Supplier<Minecraft> minecraft) {
        LOG.debug(CLIENT, "Registering entity renderers");
        RenderingRegistry.registerEntityRenderingHandler(CORED_SNOWBALL,
            rendererManager -> new SpriteRenderer<>(rendererManager, minecraft.get().getItemRenderer()));
    }
}
