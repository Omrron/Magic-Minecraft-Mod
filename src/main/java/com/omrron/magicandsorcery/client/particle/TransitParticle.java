package com.omrron.magicandsorcery.client.particle;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.omrron.magicandsorcery.particle.TransitParticleOptions;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jspecify.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class TransitParticle extends SingleQuadParticle {
    private final ItemStack itemStack;
    private final Vec3 startPos;
    private final Vec3 targetPos;
    private ParticleOptions options;

    public TransitParticle(ClientLevel level, double x, double y, double z, double targetX,
                           double targetY, double targetZ, TransitParticleOptions options, TextureAtlasSprite sprite) {
        super(level, x, y, z, targetX, targetY, targetZ, sprite);

        this.itemStack = options.itemStack();
        this.startPos = new Vec3(x, y, z);
        this.targetPos = options.targetPos();
        this.lifetime = options.duration();
        this.options = options;
        this.gravity = 0.0F;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if (this.age++ >= this.lifetime) {
            this.remove();
            return;
        }

        float pct = (float) this.age / (float) this.lifetime;

        // Linear path interpolation
        double nextX = Mth.lerp(pct, this.startPos.x, this.targetPos.x);
        double nextY = Mth.lerp(pct, this.startPos.y, this.targetPos.y);
        double nextZ = Mth.lerp(pct, this.startPos.z, this.targetPos.z);

        // Magnetic Levitation bobbing effect
        double hoverOffset = Math.sin(pct * Math.PI * 4) * 0.05;
        this.setPos(nextX, nextY + hoverOffset, nextZ);

        // Trail sparks
        if (this.level.getRandom().nextFloat() < 0.4F) {
            this.level.addParticle(
                    this.options,
                    this.x, this.y, this.z, 0, 0, 0
            );
        }
    }

    @Override
    public void render(VertexConsumer buffer, Camera camera, float partialTicks) {
        PoseStack poseStack = new PoseStack();
        Vec3 cameraPos = camera.position();

        // Calculate smooth sub-tick movement
        float pct = ((float) this.age + partialTicks) / (float) this.lifetime;
        double renderX = Mth.lerp(pct, this.startPos.x, this.targetPos.x) - cameraPos.x();
        double renderY = Mth.lerp(pct, this.startPos.y, this.targetPos.y) - cameraPos.y() + (Math.sin(pct * Math.PI * 4) * 0.05);
        double renderZ = Mth.lerp(pct, this.startPos.z, this.targetPos.z) - cameraPos.z();

        poseStack.pushPose();
        poseStack.translate(renderX, renderY, renderZ);

        // Spin the item slowly as it floats
        float rotation = (this.age + partialTicks) * 4.0F;
        poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(rotation));
        poseStack.scale(0.5F, 0.5F, 0.5F);

        MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();

        // --- NEOFORGE 26.1 (1.21.4) ITEM RENDERING PIPELINE ---
        ItemStackRenderState renderState = new ItemStackRenderState();
        ItemModelResolver itemModelResolver = Minecraft.getInstance().getItemModelResolver();

        // Resolves 3D item state (evaluates composites, properties, and overrides)
        itemModelResolver.updateForNonLiving(
                renderState,
                this.itemStack,
                ItemDisplayContext.GROUND,
                this.level,
                null,
                0
        );

        MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();

        Minecraft.getInstance().getRender().renderStatic(
                this.itemStack,
                ItemDisplayContext.GROUND,
                15728880, // Full bright light
                OverlayTexture.NO_OVERLAY,
                poseStack,
                bufferSource,
                this.level,
                0
        );

        bufferSource.endBatch();
        poseStack.popPose();
    }

    @Override
    public ParticleRenderType getGroup() {
        return ParticleRenderType.SINGLE_QUADS; // Indicates custom PoseStack rendering rather than sprite sheet batching
    }

    @Override
    protected SingleQuadParticle.Layer getLayer() {
        return SingleQuadParticle.Layer.TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<TransitParticleOptions> {

        @Override
        public @Nullable Particle createParticle(
                TransitParticleOptions transitParticleOptions,
                ClientLevel clientLevel,
                double x, double y, double z,
                double dx, double dy, double dz,
                RandomSource randomSource
        ) {
            return new TransitParticle(clientLevel, x, y, z, transitParticleOptions);
        }
    }
}