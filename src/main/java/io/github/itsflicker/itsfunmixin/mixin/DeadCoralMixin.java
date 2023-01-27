package io.github.itsflicker.itsfunmixin.mixin;

import io.github.itsflicker.itsfunmixin.helpers.DeadCoralToSandHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseCoralFanBlock;
import net.minecraft.world.level.block.BaseCoralPlantBlock;
import net.minecraft.world.level.block.BaseCoralPlantTypeBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({BaseCoralPlantBlock.class, BaseCoralFanBlock.class})
public class DeadCoralMixin extends BaseCoralPlantTypeBlock {
    public DeadCoralMixin(Properties settings) {
        super(settings);
    }

    @Override
    public void onPlace(@NotNull BlockState state, Level world, @NotNull BlockPos pos, @NotNull BlockState oldState, boolean notify) {
        if (DeadCoralToSandHelper.BREAK_CHANCE >= 0 && state.getBlock().getLootTable().getPath().contains("dead")) {
            world.scheduleTick(pos, this, DeadCoralToSandHelper.getSandDropDelay(world.getRandom()));
        }
        super.onPlace(state, world, pos, oldState, notify);
    }

    @Override
    public @NotNull BlockState updateShape(@NotNull BlockState state, @NotNull Direction direction, @NotNull BlockState neighborState, @NotNull LevelAccessor world, @NotNull BlockPos pos, @NotNull BlockPos neighborPos) {
        if (DeadCoralToSandHelper.BREAK_CHANCE >= 0 && state.getBlock().getLootTable().getPath().contains("dead")) {
            world.scheduleTick(pos, this, DeadCoralToSandHelper.getSandDropDelay(world.getRandom()));
        }
        return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public void tick(@NotNull BlockState state, @NotNull ServerLevel world, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (DeadCoralToSandHelper.BREAK_CHANCE < 0) {
            return;
        }
        if (state.getBlock().getLootTable().getPath().contains("dead") && DeadCoralToSandHelper.tryDropSand(state, world, pos, random)) {
            world.scheduleTick(pos, this, DeadCoralToSandHelper.getSandDropDelay(random));
        }
    }
}
