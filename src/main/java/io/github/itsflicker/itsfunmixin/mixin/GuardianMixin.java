package io.github.itsflicker.itsfunmixin.mixin;

import io.github.itsflicker.itsfunmixin.Main;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Guardian.class)
public class GuardianMixin extends Monster {

    protected GuardianMixin(EntityType<? extends Monster> entityType, Level world)
    {
        super(entityType, world);
    }

    @Override
    public void thunderHit(@NotNull ServerLevel serverWorld, @NotNull LightningBolt lightningEntity)
    {                                // isRemoved()
        if (Main.guardianThunderTransfer && !this.level.isClientSide && !this.isRemoved() && !((Object)this instanceof ElderGuardian))
        {
            ElderGuardian elderGuardian = new ElderGuardian(EntityType.ELDER_GUARDIAN ,this.level);
            elderGuardian.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
            elderGuardian.finalizeSpawn(serverWorld ,this.level.getCurrentDifficultyAt(elderGuardian.blockPosition()), MobSpawnType.CONVERSION, (SpawnGroupData)null, (CompoundTag)null);
            elderGuardian.setNoAi(this.isNoAi());

            if (this.hasCustomName())
            {
                elderGuardian.setCustomName(this.getCustomName());
                elderGuardian.setCustomNameVisible(this.isCustomNameVisible());
            }

            this.level.addFreshEntity(elderGuardian);
            this.discard(); // discard remove();
        }
        else
        {
            super.thunderHit(serverWorld, lightningEntity);
        }
    }

}
