package bluebird.birdzoom.mixin;

import bluebird.birdzoom.BirdZoom;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.renderer.extract.LevelExtractor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LevelExtractor.class)
public class LevelExtractorMixin {
    @ModifyExpressionValue(method = "extract", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/client/renderer/SectionOcclusionGraph;consumeFrustumUpdate()Z"))
    public boolean birdzoom$fixVisibleChunkOcclusion(boolean original) {
        return original || BirdZoom.fovChanged();
    }
}
