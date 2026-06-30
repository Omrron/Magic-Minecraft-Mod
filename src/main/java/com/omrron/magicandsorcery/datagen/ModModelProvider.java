package com.omrron.magicandsorcery.datagen;

import com.omrron.magicandsorcery.MagicandSorcery;
import com.omrron.magicandsorcery.registry.ModBlocks;
import com.omrron.magicandsorcery.registry.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.neoforged.neoforge.client.model.generators.loaders.ObjModelBuilder;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplateBuilder;


public class ModModelProvider extends ModelProvider {

    public ModModelProvider(PackOutput output) {
        super(output, MagicandSorcery.MODID);
    }

    public static final TextureSlot CRYSTAL_BASE = TextureSlot.create("mana_crystal_base", TextureSlot.ALL);
    public static final TextureSlot CRYSTAL_TOP = TextureSlot.create("mana_crystal_top", TextureSlot.ALL);

    public static final ModelTemplate MANA_CRYSTAL_TEMPLATE = ExtendedModelTemplateBuilder.builder()
            .customLoader(
                    ObjModelBuilder::new,
                    loader -> {
                            loader.modelLocation(Identifier.fromNamespaceAndPath(MagicandSorcery.MODID, "models/block/mana_crystal.obj"));
                            loader.flipV(true);
                            loader.automaticCulling(false);
                    }
            )
            .rootTransforms(transform -> transform.translation(0.5f, 0.0f, 0.5f))
            .requiredTextureSlot(TextureSlot.PARTICLE)
            .requiredTextureSlot(CRYSTAL_BASE)
            .requiredTextureSlot(CRYSTAL_TOP)
            .build();

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        generateBlockModels(blockModels);
        generateItemModels(itemModels);
    }

    private void generateBlockModels(BlockModelGenerators blockModels) {
        blockModels.createTrivialCube(ModBlocks.EXAMPLE_BLOCK.get());

        Block manaCrystal = ModBlocks.MANA_CRYSTAL.get();
        TextureMapping textures = new TextureMapping()
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(manaCrystal, "_base"))
                .put(CRYSTAL_BASE, TextureMapping.getBlockTexture(manaCrystal, "_base"))
                .put(CRYSTAL_TOP, TextureMapping.getBlockTexture(manaCrystal, "_top"));


        Identifier modelLoc = MANA_CRYSTAL_TEMPLATE.create(
                manaCrystal,
                textures,
                blockModels.modelOutput
        );

        Variant variantModel = new Variant(modelLoc);

        blockModels.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(
                        manaCrystal,
                        BlockModelGenerators.variant(variantModel)
                ).with(
                        PropertyDispatch.modify(DirectionalBlock.FACING)
                                .select(Direction.UP, BlockModelGenerators.NOP)
                                .select(Direction.DOWN, BlockModelGenerators.X_ROT_180)
                                .select(Direction.NORTH, BlockModelGenerators.X_ROT_90)
                                .select(Direction.SOUTH, BlockModelGenerators.X_ROT_90.then(BlockModelGenerators.Y_ROT_180))
                                .select(Direction.EAST, BlockModelGenerators.X_ROT_90.then(BlockModelGenerators.Y_ROT_90))
                                .select(Direction.WEST, BlockModelGenerators.X_ROT_90.then(BlockModelGenerators.Y_ROT_270))
                )
        );
    }

    private void generateItemModels(ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(ModItems.EXAMPLE_ITEM.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModBlocks.MANA_CRYSTAL.asItem(), ModelTemplates.FLAT_ITEM);
    }
}