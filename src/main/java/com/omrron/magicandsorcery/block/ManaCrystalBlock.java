package com.omrron.magicandsorcery.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.EnumMap;
import java.util.Map;

public class ManaCrystalBlock extends DirectionalBlock {
    public static final MapCodec<ManaCrystalBlock> CODEC = simpleCodec(ManaCrystalBlock::new);

    private static final Map<Direction, VoxelShape> SHAPES = new EnumMap<>(Direction.class);

    static {
        SHAPES.put(Direction.UP,    Block.box(6.0D, 0.0D, 6.0D, 10.0D, 14.0D, 10.0D));
        SHAPES.put(Direction.DOWN,  Block.box(6.0D, 2.0D, 6.0D, 10.0D, 16.0D, 10.0D));
        SHAPES.put(Direction.NORTH, Block.box(6.0D, 6.0D, 2.0D, 10.0D, 10.0D, 16.0D));
        SHAPES.put(Direction.SOUTH, Block.box(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 14.0D));
        SHAPES.put(Direction.WEST,  Block.box(2.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D));
        SHAPES.put(Direction.EAST,  Block.box(0.0D, 6.0D, 6.0D, 14.0D, 10.0D, 10.0D));
    }

    public ManaCrystalBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
    }

    @Override
    protected MapCodec<? extends DirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // Sets the crystal to face the direction of the surface clicked
        return this.defaultBlockState().setValue(FACING, context.getClickedFace());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.getOrDefault(state.getValue(FACING), SHAPES.get(Direction.UP));
    }
}