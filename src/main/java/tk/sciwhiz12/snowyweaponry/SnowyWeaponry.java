package tk.sciwhiz12.snowyweaponry;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import tk.sciwhiz12.snowyweaponry.damage.LootingSensitiveDamageSource;

@Mod(SnowyWeaponry.MODID)
public class SnowyWeaponry {
    public static final String MODID = "snowyweaponry";
    public static final Logger LOG = LogUtils.getLogger();

    public SnowyWeaponry() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        Reference.TAB_REGISTER.register(modBus);
        Reference.Items.REGISTER.register(modBus);
        Reference.EntityTypes.REGISTER.register(modBus);
        Reference.RecipeSerializers.REGISTER.register(modBus);

        modBus.register(Registration.class);
        if (FMLEnvironment.dist.isClient()) {
            modBus.register(ClientRegistration.class);
        }

        NeoForge.EVENT_BUS.register(LootingSensitiveDamageSource.class);
    }

    public static ResourceLocation loc(String path) {
        return new ResourceLocation(MODID, path);
    }
}
