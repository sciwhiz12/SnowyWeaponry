package sciwhiz12.snowyweaponry.entity;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;
import sciwhiz12.snowyweaponry.Reference.DamageSources;
import sciwhiz12.snowyweaponry.Reference.EntityTypes;

public class ExplosiveSnowballEntity extends ThrowableItemProjectile {
    public static final float EXPLOSION_POWER = 1.2F;

    public ExplosiveSnowballEntity(EntityType<ExplosiveSnowballEntity> entityType, Level world) {
        super(entityType, world);
    }

    public ExplosiveSnowballEntity(Level world, LivingEntity thrower) {
        super(EntityTypes.EXPLOSIVE_SNOWBALL, thrower, world);
    }

    public ExplosiveSnowballEntity(Level world, double x, double y, double z) {
        super(EntityTypes.EXPLOSIVE_SNOWBALL, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.SNOWBALL;
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            ItemStack stack = this.getItemRaw();
            ParticleOptions particle = stack.isEmpty() ?
                ParticleTypes.ITEM_SNOWBALL :
                new ItemParticleOption(ParticleTypes.ITEM, stack);

            for (int i = 0; i < 8; ++i) {
                this.level.addParticle(particle, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level.isClientSide) {
            this.level.explode(this,
                DamageSources.causeSnowballExplosionDamage(this.getOwner()),
                null,
                this.getX(),
                this.getY(),
                this.getZ(),
                EXPLOSION_POWER,
                false,
                Explosion.BlockInteraction.NONE);

            this.level.broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
