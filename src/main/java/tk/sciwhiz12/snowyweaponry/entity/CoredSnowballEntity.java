package tk.sciwhiz12.snowyweaponry.entity;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;
import org.checkerframework.checker.nullness.qual.Nullable;
import tk.sciwhiz12.snowyweaponry.Reference;
import tk.sciwhiz12.snowyweaponry.item.CoredSnowballItem;

public class CoredSnowballEntity extends ThrowableItemProjectile {
    public CoredSnowballEntity(EntityType<CoredSnowballEntity> entityType, Level world) {
        super(entityType, world);
    }

    public CoredSnowballEntity(Level world, LivingEntity thrower) {
        super(Reference.EntityTypes.CORED_SNOWBALL, thrower, world);
    }

    public CoredSnowballEntity(Level world, double x, double y, double z) {
        super(Reference.EntityTypes.CORED_SNOWBALL, x, y, z, world);
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
    protected Component getTypeName() {
        final ItemStack stack = this.getItem();
        if (!stack.isEmpty()) {
            return stack.getHoverName();
        }
        return super.getTypeName();
    }

    @Override
    protected void onHitEntity(EntityHitResult entityTrace) {
        super.onHitEntity(entityTrace);
        ItemStack stack = this.getItem();
        Entity entity = entityTrace.getEntity();

        int damage = 0;
        int looting = 0;
        if (Reference.Tags.FIRE_MOBS.contains(entity.getType())) {
            damage += 2; // Fire mobs damage modifier
        }

        if (stack.getItem() instanceof CoredSnowballItem item) {
            damage = item.getDamage();
            looting = item.getLootingLevel();
            @Nullable MobEffectInstance effect = item.getHitEffect();
            if (effect != null && entity instanceof LivingEntity) {
                ((LivingEntity) entity).addEffect(new MobEffectInstance(effect));
            }
        }

        entity.hurt(Reference.DamageSources.causeSnowballDamage(this, this.getOwner(), looting), damage);
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
