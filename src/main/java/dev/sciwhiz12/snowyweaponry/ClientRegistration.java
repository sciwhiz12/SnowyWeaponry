package dev.sciwhiz12.snowyweaponry;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

/**
 * Class for registering <strong>client-side only</strong> objects of this mod.
 *
 * @author SciWhiz12
 */
public final class ClientRegistration {
    private ClientRegistration() {
    } // Prevent instantiation

    @SubscribeEvent
    static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        SnowyWeaponry.LOG.debug("Registering entity renderers");
        event.registerEntityRenderer(Reference.EntityTypes.CORED_SNOWBALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(Reference.EntityTypes.EXPLOSIVE_SNOWBALL.get(), ThrownItemRenderer::new);
    }
}
