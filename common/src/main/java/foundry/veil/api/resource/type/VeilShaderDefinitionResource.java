package foundry.veil.api.resource.type;

import foundry.veil.api.client.render.shader.ShaderManager;
import foundry.veil.api.resource.VeilResourceInfo;

public record VeilShaderDefinitionResource(VeilResourceInfo resourceInfo, ShaderManager shaderManager) implements VeilShaderResource<VeilShaderDefinitionResource> {

    @Override
    public boolean canHotReload() {
        return true;
    }

    @Override
    public void hotReload() {
        this.shaderManager.scheduleRecompile(this.shaderManager.getSourceSet().getShaderDefinitionLister().fileToId(this.resourceInfo.location()));
    }
}