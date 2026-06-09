package com.omrron.magicandsorcery.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ManaCrystalBlock extends Block{
    // Defines the physical outline boundary (MinX, MinY, MinZ, MaxX, MaxY, MaxZ) in pixel units
    // This creates a centered 8x8 pixel column that goes up 14 pixels high
    private static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 8.0D, 14.0D, 8.0D);

    public ManaCrystalBlock(Properties properties) {
        super(properties);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
