package com.omrron.magicandsorcery.client.event;

import com.omrron.magicandsorcery.MagicandSorcery;
import com.omrron.magicandsorcery.client.particle.TransitParticle;
import com.omrron.magicandsorcery.registry.ModParticles;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = MagicandSorcery.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        // Links the particle type from the registry to the client-side visual renderer
        event.registerSpriteSet(ModParticles.TRANSIT.get(), TransitParticle.Provider::new);
    }
}