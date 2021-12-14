package sciwhiz12.snowyweaponry.entity;

import net.minecraft.entity.Entity;
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
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import sciwhiz12.snowyweaponry.Reference;
import sciwhiz12.snowyweaponry.Reference.EntityTypes;
import sciwhiz12.snowyweaponry.Reference.Tags;
import sciwhiz12.snowyweaponry.item.CoredSnowballItem;

public class CoredSnowballEntity extends ProjectileItemEntity {
    public CoredSnowballEntity(EntityType<CoredSnowballEntity> entityType, World world) {
        super(entityType, world);
    }

    public CoredSnowballEntity(World world, LivingEntity thrower) {
        super(EntityTypes.CORED_SNOWBALL, thrower, world);
    }

    public CoredSnowballEntity(World world, double x, double y, double z) {
        super(EntityTypes.CORED_SNOWBALL, x, y, z, world);
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
    protected ITextComponent getTypeName() {
        final ItemStack stack = this.getItem();
        if (!stack.isEmpty()) {
            return stack.getHoverName();
        }
        return super.getTypeName();
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult entityTrace) {
        super.onHitEntity(entityTrace);
        ItemStack stack = this.getItem();
        Entity entity = entityTrace.getEntity();

        int damage = 0;
        int looting = 0;
        if (Tags.FIRE_MOBS.contains(entity.getType())) {
            damage += 2; // Fire mobs damage modifier
        }

        if (stack.getItem() instanceof CoredSnowballItem) {
            CoredSnowballItem item = ((CoredSnowballItem) stack.getItem());
            damage = item.getDamage();
            looting = item.getLootingLevel();
            EffectInstance effect = item.getHitEffect();
            if (effect != null && entity instanceof LivingEntity) {
                ((LivingEntity) entity).addEffect(new EffectInstance(effect));
            }
        }

        entity.hurt(Reference.DamageSources.causeSnowballDamage(this, this.getOwner(), looting), damage);
    }

    @Override
    protected void onHit(RayTraceResult result) {
        super.onHit(result);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte) 3);
            this.remove();
        }
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
