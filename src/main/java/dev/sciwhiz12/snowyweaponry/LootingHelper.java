package dev.sciwhiz12.snowyweaponry;

import dev.sciwhiz12.snowyweaponry.entity.CoredSnowball;
import dev.sciwhiz12.snowyweaponry.item.CoredSnowballItem;
import net.neoforged.neoforge.event.enchanting.EnchantedEntityLootEvent;

public class LootingHelper {
    private LootingHelper() {
    } // Prevent instantiation

    public static void onEnchantedEntityLoot(EnchantedEntityLootEvent event) {
        if (event.getDamageSource().getDirectEntity() instanceof CoredSnowball snowball
            && snowball.getItem().getItem() instanceof CoredSnowballItem snowballItem) {
            event.setEnchantmentLevel(event.getEnchantmentLevel() + snowballItem.getLootingLevel());
        }
    }
}
