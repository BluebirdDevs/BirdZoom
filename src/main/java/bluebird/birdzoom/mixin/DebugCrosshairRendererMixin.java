package bluebird.birdzoom.mixin;

import bluebird.birdzoom.BirdZoom;
import net.minecraft.client.renderer.DebugCrosshairRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(DebugCrosshairRenderer.class)
public class DebugCrosshairRendererMixin {
    @ModifyVariable(method = "render", at = @At(value = "STORE"), index = 6)
    public float birdzoom$changeDebugCrosshair(float crosshairScale) {
        if (BirdZoom.isZoomed()) return crosshairScale * (float) BirdZoom.getMultiplier();
        return crosshairScale;
    }
}
