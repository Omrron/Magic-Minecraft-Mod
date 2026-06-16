package com.omrron.magicandsorcery.registry;

import com.omrron.magicandsorcery.MagicandSorcery;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output,
                               CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MagicandSorcery.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Makes the block break faster with a pickaxe
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.MANA_CRYSTAL.get());

        // Prevents drops/slows mining unless an Iron Pickaxe or better is used
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.MANA_CRYSTAL.get());
    }
}
