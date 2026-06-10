package com.omrron.magicandsorcery.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ManaCrystalBlock extends Block {
    // Perfectly centers a 4x4 pixel wide crystal, 14 pixels tall
    private static final VoxelShape SHAPE = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 14.0D, 10.0D);

    public ManaCrystalBlock(Properties properties) {
        super(properties);
    }

    // Overriding this Mojang-deprecated method remains the standard API practice for custom block shapes
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}