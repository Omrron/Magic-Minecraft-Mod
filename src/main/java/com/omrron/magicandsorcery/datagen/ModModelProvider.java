package com.omrron.magicandsorcery.datagen;

import com.omrron.magicandsorcery.MagicandSorcery;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

import static com.omrron.magicandsorcery.MagicandSorcery.EXAMPLE_BLOCK;
import static com.omrron.magicandsorcery.MagicandSorcery.EXAMPLE_ITEM;

public class ModModelProvider extends ModelProvider {

    public ModModelProvider(PackOutput output) {
        super(output, MagicandSorcery.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        generateBlockModels(blockModels);
        generateItemModels(itemModels);
    }

    private void generateBlockModels(BlockModelGenerators blockModels) {
        blockModels.createTrivialCube(EXAMPLE_BLOCK.get());
    }

    private void generateItemModels(ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(EXAMPLE_ITEM.get(), ModelTemplates.FLAT_ITEM);
    }
}