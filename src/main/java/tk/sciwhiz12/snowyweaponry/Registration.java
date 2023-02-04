package tk.sciwhiz12.snowyweaponry;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import tk.sciwhiz12.snowyweaponry.Reference.Items;

public final class Registration {
    private Registration() {
    } // Prevent instantiation

    @SubscribeEvent
    static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(Registration::registerDispenserBehavior);
    }

    static void registerDispenserBehavior() {
        SnowyWeaponry.LOG.debug("Registering dispenser behavior for items");
        DispenserBlock.registerBehavior(Items.IRON_CORED_SNOWBALL.get(), Reference.DispenseBehaviors.CORED_SNOWBALL);
        DispenserBlock.registerBehavior(Items.GOLD_CORED_SNOWBALL.get(), Reference.DispenseBehaviors.CORED_SNOWBALL);
        DispenserBlock.registerBehavior(Items.DIAMOND_CORED_SNOWBALL.get(), Reference.DispenseBehaviors.CORED_SNOWBALL);
        DispenserBlock.registerBehavior(Items.NETHERITE_CORED_SNOWBALL.get(), Reference.DispenseBehaviors.CORED_SNOWBALL);
        DispenserBlock.registerBehavior(Items.EXPLOSIVE_SNOWBALL.get(), Reference.DispenseBehaviors.EXPLOSIVE_SNOWBALL);
    }

    @SubscribeEvent
    static void onCreativeModeTabRegisterEvent(CreativeModeTabEvent.Register event) {
        Reference.ITEM_GROUP = event.registerCreativeModeTab(SnowyWeaponry.loc("items"), builder ->
                builder.title(Component.translatable("itemGroup.snowy_weapons"))
                        .icon(() -> new ItemStack(Items.GOLD_CORED_SNOWBALL.get())));
    }

    @SubscribeEvent
    static void onCreativeModeTabBuildContentsEvent(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == Reference.ITEM_GROUP) {
            event.accept(Items.DIAMOND_CHUNK);
            event.accept(Items.NETHERITE_NUGGET);

            event.accept(Items.IRON_CORED_SNOWBALL);
            event.accept(Items.GOLD_CORED_SNOWBALL);
            event.accept(Items.DIAMOND_CORED_SNOWBALL);
            event.accept(Items.NETHERITE_CORED_SNOWBALL);
            event.accept(Items.EXPLOSIVE_SNOWBALL);

            event.accept(Items.WAFER_CONE);
            event.accept(Items.SNOW_CONE);
            event.accept(Items.GOLDEN_SNOW_CONE);

            for (Potion potion : ForgeRegistries.POTIONS) {
                if (potion != Potions.EMPTY) {
                    event.accept(PotionUtils.setPotion(new ItemStack(Items.POTION_SNOW_CONE.get()), potion));
                }
            }
        }
    }
}
