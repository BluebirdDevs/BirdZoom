package bluebird.birdzoom.mixin;

import bluebird.birdzoom.BirdZoom;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LevelRenderer.class)
public class LevelExtractorMixin {
    @ModifyExpressionValue(method = "cullTerrain", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/client/renderer/SectionOcclusionGraph;consumeFrustumUpdate()Z"))
    public boolean birdzoom$fixVisibleChunkOcclusion(boolean original) {
        return original || BirdZoom.fovChanged();
    }
}
