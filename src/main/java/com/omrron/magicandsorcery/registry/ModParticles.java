package com.omrron.magicandsorcery.registry;

import com.mojang.serialization.MapCodec;
import com.omrron.magicandsorcery.MagicandSorcery;
import com.omrron.magicandsorcery.particle.TransitParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(Registries.PARTICLE_TYPE, MagicandSorcery.MODID);

    public static final DeferredHolder<ParticleType<?>, ParticleType<TransitParticleOptions>> TRANSIT =
            PARTICLES.register("transit", () -> new ParticleType<>(false) {
                @Override
                public MapCodec<TransitParticleOptions> codec() {
                    return TransitParticleOptions.CODEC;
                }

                @Override
                public StreamCodec<? super RegistryFriendlyByteBuf, TransitParticleOptions> streamCodec() {
                    return TransitParticleOptions.STREAM_CODEC;
                }
            });

    public static void register(IEventBus eventBus) {
        PARTICLES.register(eventBus);
    }
}