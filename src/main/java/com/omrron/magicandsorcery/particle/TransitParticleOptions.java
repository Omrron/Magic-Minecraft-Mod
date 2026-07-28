package com.omrron.magicandsorcery.particle;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.omrron.magicandsorcery.registry.ModParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public record TransitParticleOptions(ItemStack itemStack, Vec3 targetPos, int duration) implements ParticleOptions {

    public static final MapCodec<TransitParticleOptions> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ItemStack.CODEC.fieldOf("item").forGetter(TransitParticleOptions::itemStack),
            Vec3.CODEC.fieldOf("target").forGetter(TransitParticleOptions::targetPos),
            Codec.INT.fieldOf("duration").forGetter(TransitParticleOptions::duration)
    ).apply(instance, TransitParticleOptions::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, TransitParticleOptions> STREAM_CODEC = StreamCodec.composite(
            ItemStack.STREAM_CODEC, TransitParticleOptions::itemStack,
            Vec3.STREAM_CODEC, TransitParticleOptions::targetPos,
            ByteBufCodecs.VAR_INT, TransitParticleOptions::duration,
            TransitParticleOptions::new
    );

    @Override
    public ParticleType<?> getType() {
        return ModParticles.TRANSIT.get();
    }
}