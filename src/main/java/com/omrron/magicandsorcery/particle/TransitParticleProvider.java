package com.omrron.magicandsorcery.particle;

import com.omrron.magicandsorcery.client.particle.TransitParticle;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class TransitParticleProvider implements ParticleProvider<TransitParticleOptions> {
    private final SpriteSet sprites;

    public TransitParticleProvider(SpriteSet sprites) {
        this.sprites = sprites;
    }

    @Override
    public @Nullable Particle createParticle(@NonNull TransitParticleOptions transitParticleOptions, ClientLevel level,
                                             double x, double y, double z, double dx, double dy, double dz, @NonNull RandomSource randomSource) {
        return new TransitParticle(level, x, y, z, dx, dy, dz, transitParticleOptions, this.sprites.get(randomSource));
    }
}
