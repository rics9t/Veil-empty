package foundry.veil.api.resource.type;

import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.resource.VeilResourceInfo;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.util.profiling.InactiveProfiler;

import java.util.concurrent.CompletableFuture;

public record FramebufferResource(VeilResourceInfo resourceInfo) implements VeilTextResource<FramebufferResource> {

    @Override
    public boolean canHotReload() {
        return true;
    }

    @Override
    public void hotReload() {
        // TODO add way to reload single framebuffer
        Minecraft client = Minecraft.getInstance();
        VeilRenderSystem.renderer().getFramebufferManager().reload(CompletableFuture::completedFuture, client.getResourceManager(), InactiveProfiler.INSTANCE, InactiveProfiler.INSTANCE, Util.backgroundExecutor(), client);
    }
}