package com.omrron.magicandsorcery;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModRegistries {
    // This creates the "bucket" that holds your items
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MagicandSorcery.MODID);
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MagicandSorcery.MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "magicandsorcery" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MagicandSorcery.MODID);
}