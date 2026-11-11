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
@Mixin(value = LevelRenderer.class, priority = 1200)
*///?}
public class LevelExtractorMixin {
    //? if >= 26.2 {
    @ModifyExpressionValue(method = "extract", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/client/renderer/SectionOcclusionGraph;consumeFrustumUpdate()Z"))
    //?} else if >= 1.21.9 {
    /*@ModifyExpressionValue(method = "cullTerrain", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/client/renderer/SectionOcclusionGraph;consumeFrustumUpdate()Z"), require = 0)
    *///?} else if > 1.20.1 {
    /*@ModifyExpressionValue(method = "setupRender", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/client/renderer/SectionOcclusionGraph;consumeFrustumUpdate()Z"), require = 0)
    *///?} else {
    /*@ModifyExpressionValue(method = "setupRender", at = @At(value = "INVOKE", ordinal = 0, target = "Ljava/util/concurrent/atomic/AtomicBoolean;compareAndSet(ZZ)Z"), require = 0)
    *///?}
    public boolean birdzoom$fixVisibleChunkOcclusion(boolean original) {
        return original || BirdZoom.fovChanged();
    }
}
