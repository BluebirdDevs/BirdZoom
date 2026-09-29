package bluebird.birdzoom.mixin;

import bluebird.birdzoom.BirdZoom;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

//? >= 26.2 {
import net.minecraft.client.renderer.extract.LevelExtractor;
@Mixin(LevelExtractor.class)
//?} else {
/*import net.minecraft.client.renderer.LevelRenderer;
@Mixin(LevelRenderer.class)
*///?}
public class LevelExtractorMixin {
    //? if >= 26.2 {
    @ModifyExpressionValue(method = "extract", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/client/renderer/SectionOcclusionGraph;consumeFrustumUpdate()Z"))
    //?} else if >= 1.21.9 {
    /*@ModifyExpressionValue(method = "cullTerrain", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/client/renderer/SectionOcclusionGraph;consumeFrustumUpdate()Z"))
    *///?} else if > 1.20.1 {
    /*@ModifyExpressionValue(method = "setupRender", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/client/renderer/SectionOcclusionGraph;consumeFrustumUpdate()Z"))
    *///?} else {
    /*@ModifyExpressionValue(method = "setupRender", at = @At(value = "INVOKE", ordinal = 0, target = "Ljava/util/concurrent/atomic/AtomicBoolean;compareAndSet(ZZ)Z"))
    *///?}
    public boolean birdzoom$fixVisibleChunkOcclusion(boolean original) {
        return original || BirdZoom.fovChanged();
    }
}
