package tk.sciwhiz12.snowyweaponry.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.checkerframework.checker.nullness.qual.Nullable;
import tk.sciwhiz12.snowyweaponry.Reference;
import tk.sciwhiz12.snowyweaponry.Reference.Items;
import tk.sciwhiz12.snowyweaponry.item.CoredSnowballItem;

public class CoredSnowball extends Snowball {
    public CoredSnowball(EntityType<CoredSnowball> entityType, Level level) {
        super(entityType, level);
    }

    public CoredSnowball(Level level, double x, double y, double z) {
        super(Reference.EntityTypes.CORED_SNOWBALL.get(), level);
        setPos(x, y, z);
    }

    public CoredSnowball(Level level, LivingEntity thrower) {
        this(level, thrower.getX(), thrower.getEyeY() - 0.1D, thrower.getZ());
        setOwner(thrower);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.IRON_CORED_SNOWBALL.get();
    }

    @Override
    protected Component getTypeName() {
        final ItemStack stack = this.getItem();
        if (!stack.isEmpty()) {
            return stack.getHoverName();
        }
        return super.getTypeName();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        ItemStack stack = this.getItem();
        Entity entity = result.getEntity();

        int damage = 0;
        int looting = 0;
        if (entity.getType().is(Reference.Tags.FIRE_MOBS)) {
            damage += 2; // Fire mobs damage modifier
        }

        if (stack.getItem() instanceof CoredSnowballItem item) {
            damage = item.getDamage();
            looting = item.getLootingLevel();
            @Nullable MobEffectInstance effect = item.getHitEffect();
            if (effect != null && entity instanceof LivingEntity livingEntity) {
                livingEntity.addEffect(new MobEffectInstance(effect));
            }
        }

        entity.hurt(Reference.DamageSources.causeSnowballDamage(this, this.getOwner(), looting), damage);
    }
}
