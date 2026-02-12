package foundry.veil.api.resource.type;

import foundry.veil.api.resource.VeilResource;
import foundry.veil.api.resource.VeilResourceInfo;

public record UnknownResource(VeilResourceInfo resourceInfo) implements VeilResource<UnknownResource> {

    @Override
    public boolean canHotReload() {
        return false;
    }

    @Override
    public void hotReload() {
    }
}
