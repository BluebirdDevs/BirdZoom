package bluebird.birdzoom.mixin;

import bluebird.birdzoom.BirdZoom;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(DebugScreenOverlay.class)
public class DebugCrosshairRendererMixin {
    @ModifyVariable(method = "render3dCrosshair", at = @At(value = "STORE"), index = 4)
    public float birdzoom$changeDebugCrosshair(float crosshairScale) {
        if (BirdZoom.isZoomed()) return crosshairScale * (float) BirdZoom.getMultiplier();
        return crosshairScale;
    }
}
