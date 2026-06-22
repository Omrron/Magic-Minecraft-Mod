package com.omrron.magicandsorcery.datagen;

import com.omrron.magicandsorcery.registry.ModBlocks;
import com.omrron.magicandsorcery.registry.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class ModBlockLoot extends BlockLootSubProvider {

    public ModBlockLoot(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, registries);
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.MANA_CRYSTAL.get());
        this.add(ModBlocks.EXAMPLE_BLOCK.get(), noDrop());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream()
                .map(holder -> (Block) holder.get())
                .toList();
    }
}