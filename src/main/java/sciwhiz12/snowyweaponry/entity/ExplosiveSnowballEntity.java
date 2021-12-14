package sciwhiz12.snowyweaponry.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import sciwhiz12.snowyweaponry.Reference.DamageSources;
import sciwhiz12.snowyweaponry.Reference.EntityTypes;

public class ExplosiveSnowballEntity extends ProjectileItemEntity {
    public static final float EXPLOSION_POWER = 1.2F;

    public ExplosiveSnowballEntity(EntityType<ExplosiveSnowballEntity> entityType, World world) {
        super(entityType, world);
    }

    public ExplosiveSnowballEntity(World world, LivingEntity thrower) {
        super(EntityTypes.EXPLOSIVE_SNOWBALL, thrower, world);
    }

    public ExplosiveSnowballEntity(World world, double x, double y, double z) {
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
            IParticleData particle = stack.isEmpty() ?
                ParticleTypes.ITEM_SNOWBALL :
                new ItemParticleData(ParticleTypes.ITEM, stack);

            for (int i = 0; i < 8; ++i) {
                this.level.addParticle(particle, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void onHit(RayTraceResult result) {
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
                Explosion.Mode.NONE);

            this.level.broadcastEntityEvent(this, (byte) 3);
            this.remove();
        }
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
