package com.omrron.magicandsorcery.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ManaCrystalBlock extends RotatedPillarBlock {
    private static final VoxelShape SHAPE_Y = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 14.0D, 10.0D);
    private static final VoxelShape SHAPE_Z = Block.box(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 14.0D);
    private static final VoxelShape SHAPE_X = Block.box(0.0D, 6.0D, 6.0D, 14.0D, 10.0D, 10.0D);

    public ManaCrystalBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.Y));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(AXIS)) {
            case X -> SHAPE_X;
            case Z -> SHAPE_Z;
            case Y -> SHAPE_Y;
        };
    }
}