package com.omrron.magicandsorcery.registry;

import net.neoforged.bus.api.IEventBus;

public class ModRegistries {
    public static void register(IEventBus modEventBus) {
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
    }
}