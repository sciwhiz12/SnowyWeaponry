package tk.sciwhiz12.snowyweaponry;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

/**
 * Class for registering <strong>client-side only</strong> objects of this mod.
 *
 * @author SciWhiz12
 */
@EventBusSubscriber(modid = SnowyWeaponry.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public final class ClientRegistration {
    private ClientRegistration() {
    } // Prevent instantiation

    @SubscribeEvent
    static void onColorHandlerItem(ColorHandlerEvent.Item event) {
        SnowyWeaponry.LOG.debug(SnowyWeaponry.CLIENT, "Registering item colors");
        event.getItemColors().register(
                (stack, index) -> index != 1 ? -1 : PotionUtils.getColor(stack),
                Reference.Items.POTION_SNOW_CONE
        );
    }

    @SubscribeEvent
    static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        SnowyWeaponry.LOG.debug(SnowyWeaponry.CLIENT, "Registering entity renderers");
        event.registerEntityRenderer(Reference.EntityTypes.CORED_SNOWBALL, ThrownItemRenderer::new);
        event.registerEntityRenderer(Reference.EntityTypes.EXPLOSIVE_SNOWBALL, ThrownItemRenderer::new);
    }
}
