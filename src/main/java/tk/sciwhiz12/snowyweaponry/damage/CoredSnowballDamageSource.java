package tk.sciwhiz12.snowyweaponry.damage;

import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.checkerframework.checker.nullness.qual.Nullable;

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
        if (event.getDamageSource() instanceof CoredSnowballDamageSource source) {
            int originalLooting = event.getLootingLevel();
            int snowballLooting = source.getLootingLevel();
            event.setLootingLevel(originalLooting + snowballLooting);
        }
    }
}
