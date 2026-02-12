package foundry.veil.api.resource.type;

import foundry.veil.api.resource.VeilResource;
import foundry.veil.api.resource.VeilResourceInfo;

public record TextureResource(VeilResourceInfo resourceInfo) implements VeilResource<TextureResource> {

    @Override
    public boolean canHotReload() {
        return true;
    }

    @Override
    public void hotReload() {
    }
}