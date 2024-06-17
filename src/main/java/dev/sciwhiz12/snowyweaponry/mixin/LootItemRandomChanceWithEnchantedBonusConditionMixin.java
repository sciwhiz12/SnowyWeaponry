package dev.sciwhiz12.snowyweaponry.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.sciwhiz12.snowyweaponry.LootingHelper;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LootItemRandomChanceWithEnchantedBonusCondition.class)
public class LootItemRandomChanceWithEnchantedBonusConditionMixin {

    @WrapOperation(
            method = "test(Lnet/minecraft/world/level/storage/loot/LootContext;)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getEnchantmentLevel(Lnet/minecraft/core/Holder;Lnet/minecraft/world/entity/LivingEntity;)I"
            )
    )
    private int snowyweaponry$lootingOnGoldSnowball(Holder<Enchantment> enchantment, LivingEntity attacker, Operation<Integer> original, @Local(argsOnly = true) LootContext context) {
        final int lootingLevel = LootingHelper.handleSnowballLooting(context);
        if (lootingLevel >= 0) {
            return lootingLevel;
        }
        return original.call(enchantment, attacker);
    }

}
