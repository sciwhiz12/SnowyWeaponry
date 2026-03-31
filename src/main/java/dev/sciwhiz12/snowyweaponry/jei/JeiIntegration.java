package dev.sciwhiz12.snowyweaponry.jei;

import dev.sciwhiz12.snowyweaponry.Reference;
import dev.sciwhiz12.snowyweaponry.SnowyWeaponry;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.resources.Identifier;

@JeiPlugin
public class JeiIntegration implements IModPlugin {
    private static final Identifier PLUGIN_UID = SnowyWeaponry.id("jei_plugin");

    public JeiIntegration() { // Required for JEI plugins, see annotation javadocs
    }

    @Override
    public Identifier getPluginUid() {
        return PLUGIN_UID;
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(Reference.Items.POTION_SNOW_CONE.get(), PotionSubtypeInterpreter.INSTANCE);
    }
}
