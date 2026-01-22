package chiefarug.mods.manastorm_reactor.mixin;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.PushReaction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vazkii.botania.common.entity.ManaStormEntity;

import static chiefarug.mods.manastorm_reactor.ManaStormReactor.FACTOR;

@MethodsReturnNonnullByDefault
@Mixin(value = ManaStormEntity.class, remap = false)
public abstract class ManaStormEntityMixin extends Entity {
    public ManaStormEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    // very op being able to move these around.
    @Unique
    @Override
    public PushReaction getPistonPushReaction() {
        return PushReaction.IGNORE;
    }


    @ModifyArg(method = "spawnBurst",
               at = @At(value = "INVOKE",
                        target = "Lvazkii/botania/common/entity/ManaBurstEntity;setMana(I)V"))
    private int manastormreactor$modifyCurrent(int orig) {
        return (int) (orig * FACTOR.get());
    }
    @ModifyArg(method = "spawnBurst",
               at = @At(value = "INVOKE",
                        target = "Lvazkii/botania/common/entity/ManaBurstEntity;setStartingMana(I)V"))
    private int manastormreactor$modifyInitial(int orig) {
        return (int) (orig * FACTOR.get());
    }

    @ModifyArg(method = "spawnBurst",
               at = @At(value = "INVOKE",
                        target = "Lvazkii/botania/common/entity/ManaBurstEntity;setManaLossPerTick(F)V"))
    private float manastormreactor$modifyLoss(float orig) {
        return (float) (orig * FACTOR.get());
    }
}
