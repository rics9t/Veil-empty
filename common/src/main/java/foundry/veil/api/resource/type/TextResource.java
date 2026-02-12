package foundry.veil.api.resource.type;

import foundry.veil.api.resource.VeilResourceInfo;

public record TextResource(VeilResourceInfo resourceInfo, Type type) implements VeilTextResource<TextResource> {

    @Override
    public boolean canHotReload() {
        return false;
    }

    @Override
    public void hotReload() {
    }

    public enum Type {
        TEXT(".txt"),
        JSON(".json");

        private final String extension;

        Type(String extension) {
            this.extension = extension;
        }

        public String getExtension() {
            return this.extension;
        }
    }
}