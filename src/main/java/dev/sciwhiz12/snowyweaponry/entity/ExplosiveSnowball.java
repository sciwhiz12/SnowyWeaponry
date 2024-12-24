package dev.sciwhiz12.snowyweaponry.entity;

import dev.sciwhiz12.snowyweaponry.Reference;
import dev.sciwhiz12.snowyweaponry.Reference.DamageTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class ExplosiveSnowball extends Snowball {
    public static final float EXPLOSION_POWER = 1.2F;

    public ExplosiveSnowball(EntityType<ExplosiveSnowball> entityType, Level level) {
        super(entityType, level);
    }

    public ExplosiveSnowball(Level level, double x, double y, double z, ItemStack stack) {
        super(Reference.EntityTypes.EXPLOSIVE_SNOWBALL.get(), level);
        setPos(x, y, z); // Copied from ThrowableProjectile constructor
        setItem(stack); // Copied from ThrowableItemProjectile constructor
    }

    public ExplosiveSnowball(Level level, LivingEntity thrower, ItemStack stack) {
        // Copied from ThrowableItemProjectile constructor
        this(level, thrower.getX(), thrower.getEyeY() - 0.1D, thrower.getZ(), stack);
        setOwner(thrower);
    }

    @Override
    protected Item getDefaultItem() {
        return Reference.Items.EXPLOSIVE_SNOWBALL.get();
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        //noinspection resource
        final Level level = this.level();
        if (!level.isClientSide) {
            final Registry<DamageType> damageTypes = level.registryAccess().lookup(Registries.DAMAGE_TYPE).orElseThrow();
            final Holder.Reference<DamageType> damageType = damageTypes.getOrThrow(DamageTypes.CORED_SNOWBALL_EXPLOSION);
            level.explode(this,
                    new DamageSource(damageType, this, this.getOwner()),
                    null,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    EXPLOSION_POWER,
                    false,
                    Level.ExplosionInteraction.NONE);
        }
    }
}
