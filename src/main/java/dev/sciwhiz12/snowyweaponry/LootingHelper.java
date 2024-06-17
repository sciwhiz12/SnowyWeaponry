package dev.sciwhiz12.snowyweaponry;

import dev.sciwhiz12.snowyweaponry.Reference.Items;
import dev.sciwhiz12.snowyweaponry.entity.CoredSnowball;
import dev.sciwhiz12.snowyweaponry.item.CoredSnowballItem;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

public class LootingHelper {
    private LootingHelper() {
    } // Prevent instantiation

    public static int handleSnowballLooting(LootContext context) {
        // The attacker passed to this method is not the attacker we need -- we need the direct attacker
        // We obtain it directly from the LootContext instead (only if it's available)
        if (context.getParamOrNull(LootContextParams.DIRECT_ATTACKING_ENTITY) instanceof CoredSnowball snowball
            && snowball.getItem().is(Items.GOLD_CORED_SNOWBALL)
            && snowball.getItem().getItem() instanceof CoredSnowballItem snowballItem) {

            return snowballItem.getLootingLevel();
        }

        return -1;
    }
}
