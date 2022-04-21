package tk.sciwhiz12.snowyweaponry;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tk.sciwhiz12.snowyweaponry.damage.CoredSnowballDamageSource;

@Mod(SnowyWeaponry.MODID)
public class SnowyWeaponry {
    public static final String MODID = "snowyweaponry";
    public static final Logger LOG = LogManager.getLogger();

    public SnowyWeaponry() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        Reference.Items.REGISTER.register(modBus);
        Reference.EntityTypes.REGISTER.register(modBus);
        Reference.RecipeSerializers.REGISTER.register(modBus);
        
        modBus.register(Registration.class);
        if (FMLEnvironment.dist.isClient()) {
            modBus.register(ClientRegistration.class);
        }

        MinecraftForge.EVENT_BUS.register(CoredSnowballDamageSource.class);
    }

    public static ResourceLocation loc(String path) {
        return new ResourceLocation(MODID, path);
    }
}
