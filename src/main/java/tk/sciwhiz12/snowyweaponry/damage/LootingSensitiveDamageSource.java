package tk.sciwhiz12.snowyweaponry.damage;

import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.checkerframework.checker.nullness.qual.Nullable;

public class LootingSensitiveDamageSource extends DamageSource {
    private final int lootingLevel;

    public LootingSensitiveDamageSource(Holder<DamageType> type, @Nullable Entity directEntity, @Nullable Entity causingEntity, @Nullable Vec3 damageSourcePosition, int lootingLevel) {
        super(type, directEntity, causingEntity, damageSourcePosition);
        this.lootingLevel = lootingLevel;
    }

    public int getLootingLevel() {
        return lootingLevel;
    }

    @SubscribeEvent
    static void onLootingLevel(LootingLevelEvent event) {
        if (event.getDamageSource() instanceof LootingSensitiveDamageSource source) {
            int originalLooting = event.getLootingLevel();
            int snowballLooting = source.getLootingLevel();
            event.setLootingLevel(originalLooting + snowballLooting);
        }
    }
}
