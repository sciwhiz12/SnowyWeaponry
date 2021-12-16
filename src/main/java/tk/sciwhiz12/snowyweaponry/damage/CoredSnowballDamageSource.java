package tk.sciwhiz12.snowyweaponry.damage;

import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import org.checkerframework.checker.nullness.qual.Nullable;
import tk.sciwhiz12.snowyweaponry.SnowyWeaponry;

@EventBusSubscriber(modid = SnowyWeaponry.MODID, bus = Bus.FORGE)
public class CoredSnowballDamageSource extends IndirectEntityDamageSource {
    private final int lootingLevel;

    public CoredSnowballDamageSource(String msgId, Entity entity, @Nullable Entity owner, int lootingLevel) {
        super(msgId, entity, owner);
        this.lootingLevel = lootingLevel;
    }

    public int getLootingLevel() {
        return lootingLevel;
    }

    @SubscribeEvent
    static void onLootingLevel(LootingLevelEvent event) {
        if (event.getDamageSource() instanceof CoredSnowballDamageSource) {
            int originalLooting = event.getLootingLevel();
            int snowballLooting = ((CoredSnowballDamageSource) event.getDamageSource()).getLootingLevel();
            event.setLootingLevel(originalLooting + snowballLooting);
        }
    }
}
