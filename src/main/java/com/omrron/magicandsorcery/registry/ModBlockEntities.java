package com.omrron.magicandsorcery.registry;

import com.omrron.magicandsorcery.MagicandSorcery;
import com.omrron.magicandsorcery.block.entity.ManaRouterBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MagicandSorcery.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ManaRouterBlockEntity>> MANA_ROUTER =
            BLOCK_ENTITIES.register("mana_router",
                    () -> new BlockEntityType<ManaRouterBlockEntity>()
                                );

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}