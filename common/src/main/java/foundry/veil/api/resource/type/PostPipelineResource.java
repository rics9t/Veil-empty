package foundry.veil.api.resource.type;

import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.resource.VeilResourceInfo;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.util.profiling.InactiveProfiler;

import java.util.concurrent.CompletableFuture;

public record PostPipelineResource(VeilResourceInfo resourceInfo) implements VeilTextResource<PostPipelineResource> {

    @Override
    public boolean canHotReload() {
        return true;
    }

    @Override
    public void hotReload() {
        // TODO add way to reload single pipeline
        Minecraft client = Minecraft.getInstance();
        VeilRenderSystem.renderer().getPostProcessingManager().reload(CompletableFuture::completedFuture, client.getResourceManager(), InactiveProfiler.INSTANCE, InactiveProfiler.INSTANCE, Util.backgroundExecutor(), client);
    }
}