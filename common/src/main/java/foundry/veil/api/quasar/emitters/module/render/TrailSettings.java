package foundry.veil.api.quasar.emitters.module.render;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import foundry.veil.api.quasar.fx.Trail;
import foundry.veil.api.util.CodecUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector4f;
import org.joml.Vector4fc;

public class TrailSettings {

    public static final Codec<TrailSettings> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.optionalFieldOf("trailFrequency", 1).forGetter(settings -> settings.trailFrequency),
            Codec.INT.optionalFieldOf("trailLength", 20).forGetter(settings -> settings.trailLength),
            CodecUtil.VECTOR4F_CODEC.optionalFieldOf("trailColor", new Vector4f(1.0F)).forGetter(settings -> settings.trailColor),
            Codec.FLOAT.fieldOf("trailWidthModifier").forGetter(settings -> settings.trailWidthModifierFloat),
            ResourceLocation.CODEC.fieldOf("trailTexture").forGetter(settings -> settings.trailTexture),
            Codec.FLOAT.fieldOf("trailPointModifier").forGetter(settings -> 1.0F),
            Trail.TilingMode.CODEC.optionalFieldOf("tilingMode", Trail.TilingMode.STRETCH).forGetter(settings -> settings.tilingMode),
            Codec.BOOL.optionalFieldOf("billboard", true).forGetter(settings -> settings.billboard),
            Codec.BOOL.optionalFieldOf("parentRotation", false).forGetter(settings -> settings.parentRotation)
    ).apply(instance, TrailSettings::new));

    private int trailFrequency;
    private int trailLength;
    private Vector4f trailColor;
    private TrailWidthModifier trailWidthModifier;
    private TrailPointModifier trailPointModifier;
    private ResourceLocation trailTexture;
    private Trail.TilingMode tilingMode;
    private boolean billboard;
    private boolean parentRotation;
    private float trailWidthModifierFloat = 1f;

    public TrailSettings(int trailFrequency, int trailLength, Vector4fc trailColor, TrailWidthModifier trailWidthModifier, ResourceLocation trailTexture, TrailPointModifier trailPointModifier, Trail.TilingMode tilingMode, boolean billboard, boolean parentRotation) {
        this.trailFrequency = trailFrequency;
        this.trailLength = trailLength;
        this.trailColor = new Vector4f(trailColor);
        this.trailWidthModifier = trailWidthModifier;
        this.trailTexture = trailTexture;
        this.trailPointModifier = trailPointModifier;
        this.tilingMode = tilingMode;
        this.billboard = billboard;
        this.parentRotation = parentRotation;
    }

    private TrailSettings(int trailFrequency, int trailLength, Vector4fc trailColor, float trailWidthModifier, ResourceLocation trailTexture, float trailPointModifier, Trail.TilingMode tilingMode, boolean billboard, boolean parentRotation) {
        this.trailFrequency = trailFrequency;
        this.trailLength = trailLength;
        this.trailColor = new Vector4f(trailColor);
        this.trailWidthModifier = (width, ageScale) -> ((float) Math.sin(width * 3.15) / 2f) * trailWidthModifier * this.trailWidthModifierFloat;
        this.trailTexture = trailTexture;
        this.trailPointModifier = (point, index, velocity) -> point;
        this.tilingMode = tilingMode;
        this.billboard = billboard;
        this.parentRotation = parentRotation;
    }

    public void setParentRotation(boolean parentRotation) {
        this.parentRotation = parentRotation;
    }

    public boolean getParentRotation() {
        return this.parentRotation;
    }

    public void setBillboard(boolean billboard) {
        this.billboard = billboard;
    }

    public boolean getBillboard() {
        return this.billboard;
    }

    public void setTilingMode(Trail.TilingMode tilingMode) {
        this.tilingMode = tilingMode;
    }

    public Trail.TilingMode getTilingMode() {
        return this.tilingMode;
    }

    public void setTrailPointModifier(TrailPointModifier trailPointModifier) {
        this.trailPointModifier = trailPointModifier;
    }

    public TrailPointModifier getTrailPointModifier() {
        return this.trailPointModifier;
    }

    public void setTrailFrequency(int trailFrequency) {
        this.trailFrequency = trailFrequency;
    }

    public void setTrailLength(int trailLength) {
        this.trailLength = trailLength;
    }

    public void setTrailColor(Vector4f trailColor) {
        this.trailColor = trailColor;
    }

    public void setTrailWidthModifier(TrailWidthModifier trailWidthModifier) {
        this.trailWidthModifier = trailWidthModifier;
    }

    public void setTrailTexture(ResourceLocation trailTexture) {
        this.trailTexture = trailTexture;
    }

    public int getTrailFrequency() {
        return this.trailFrequency;
    }

    public int getTrailLength() {
        return this.trailLength;
    }

    public Vector4f getTrailColor() {
        return this.trailColor;
    }

    public TrailWidthModifier getTrailWidthModifier() {
        return this.trailWidthModifier;
    }

    public ResourceLocation getTrailTexture() {
        return this.trailTexture;
    }

    @FunctionalInterface
    public interface TrailPointModifier {
        Vector4f modify(Vector4f point, Integer index, Vec3 velocity);
    }

    @FunctionalInterface
    public interface TrailWidthModifier {
        float modify(float ageScale, double ageMultiplier);
    }
}