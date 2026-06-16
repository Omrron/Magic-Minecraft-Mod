package com.omrron.magicandsorcery.registry;

import com.omrron.magicandsorcery.MagicandSorcery;
import com.omrron.magicandsorcery.block.ManaCrystalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

import net.minecraft.resources.Identifier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MagicandSorcery.MODID);

    public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock(
            "example_block",
            p -> p.mapColor(MapColor.STONE)
    );

    public static final DeferredBlock<Block> MANA_CRYSTAL = BLOCKS.registerBlock(
            "mana_crystal",
            p -> new ManaCrystalBlock(p.mapColor(MapColor.LAPIS)
                    .destroyTime(1.5f)
                    .explosionResistance(3.0f)
                    .sound(SoundType.AMETHYST)
                    .requiresCorrectToolForDrops()
                    .lightLevel(state -> 7)
                    .noOcclusion())
    );


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> blockSupplier) {
        Identifier registryKey = Identifier.fromNamespaceAndPath(MagicandSorcery.MODID, name);

        return BLOCKS.register(name, blockSupplier);
    }
}
