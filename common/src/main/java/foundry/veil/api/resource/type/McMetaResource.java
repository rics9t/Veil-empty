package foundry.veil.api.resource.type;

import foundry.veil.api.resource.VeilResource;
import foundry.veil.api.resource.VeilResourceInfo;
import net.minecraft.server.packs.resources.ResourceMetadata;

public record McMetaResource(VeilResourceInfo resourceInfo, ResourceMetadata metadata) implements VeilResource<McMetaResource> {

    @Override
    public boolean canHotReload() {
        return false;
    }

    @Override
    public void hotReload() {
    }
}
