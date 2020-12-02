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
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import sciwhiz12.snowyweaponry.Reference;
import sciwhiz12.snowyweaponry.Reference.Tags;
import sciwhiz12.snowyweaponry.item.CoredSnowballItem;

import static sciwhiz12.snowyweaponry.Reference.EntityTypes;

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
    public void handleStatusUpdate(byte id) {
        if (id == 3) {
            ItemStack stack = this.func_213882_k();
            IParticleData particle = stack.isEmpty() ?
                ParticleTypes.ITEM_SNOWBALL :
                new ItemParticleData(ParticleTypes.ITEM, stack);

            for (int i = 0; i < 8; ++i) {
                this.world.addParticle(particle, this.getPosX(), this.getPosY(), this.getPosZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    @Override
    protected void onEntityHit(EntityRayTraceResult entityTrace) {
        super.onEntityHit(entityTrace);
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
                ((LivingEntity) entity).addPotionEffect(new EffectInstance(effect));
            }
        }

        entity.attackEntityFrom(Reference.DamageSources.causeSnowballDamage(this, this.func_234616_v_(), looting), damage);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            this.remove();
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
