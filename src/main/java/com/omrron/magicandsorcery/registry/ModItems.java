package com.omrron.magicandsorcery.registry;

import com.omrron.magicandsorcery.MagicandSorcery;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.omrron.magicandsorcery.registry.ModBlocks.EXAMPLE_BLOCK;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MagicandSorcery.MODID);

    // Creates a new BlockItem with the id "magicandsorcery:example_block", combining the namespace and path
    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block", EXAMPLE_BLOCK);
    public static final DeferredItem<BlockItem> MANA_CRYSTAL_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(
            "mana_crystal_block",
            ModBlocks.MANA_CRYSTAL
    );

    public static final DeferredItem<Item> MANA_CRYSTAL_ITEM = ITEMS.registerSimpleItem(
            "mana_crystal_item",
            p -> p.rarity(Rarity.UNCOMMON));

    // Creates a new food item with the id "magicandsorcery:example_id", nutrition 1 and saturation 2
    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", p -> p.food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(5).saturationModifier(2f).build()));
}
