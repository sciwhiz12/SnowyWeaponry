package dev.sciwhiz12.snowyweaponry;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.component.DataComponents;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

/**
 * Class for registering <strong>client-side only</strong> objects of this mod.
 *
 * @author SciWhiz12
 */
public final class ClientRegistration {
    private ClientRegistration() {
    } // Prevent instantiation

    @SubscribeEvent
    static void onColorHandlerItem(RegisterColorHandlersEvent.Item event) {
        SnowyWeaponry.LOG.debug("Registering item colors");
        event.register(
                (stack, index) -> index != 1 ? -1 : stack.get(DataComponents.POTION_CONTENTS).getColor(),
                Reference.Items.POTION_SNOW_CONE.get()
        );
    }

    @SubscribeEvent
    static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        SnowyWeaponry.LOG.debug("Registering entity renderers");
        event.registerEntityRenderer(Reference.EntityTypes.CORED_SNOWBALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(Reference.EntityTypes.EXPLOSIVE_SNOWBALL.get(), ThrownItemRenderer::new);
    }
}
