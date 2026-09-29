package bluebird.birdzoom.mixin;

import bluebird.birdzoom.BirdZoom;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

//? > 26.1 {
import net.minecraft.client.renderer.DebugCrosshairRenderer;
@Mixin(DebugCrosshairRenderer.class)
//?} else {
/*import net.minecraft.client.gui.components.DebugScreenOverlay;
@Mixin(DebugScreenOverlay.class)
*///?}
public class DebugCrosshairRendererMixin {
    //? >= 26.3 {
    @ModifyVariable(method = "render", at = @At(value = "STORE"), index = 6)
    //?} else if > 26.1 {
    /*@ModifyVariable(method = "render", at = @At(value = "STORE"), index = 4)
    *///?} else if > 1.21.11 {
    /*@ModifyVariable(method = "render3dCrosshair", at = @At(value = "STORE"), index = 4)
    *///?} else if > 1.21.5 {
    /*@ModifyVariable(method = "render3dCrosshair", at = @At(value = "STORE"), index = 3)
    *///?} else {
    //?}
    //? if > 1.21.5 {
    public float birdzoom$changeDebugCrosshair(float crosshairScale) {
        if (BirdZoom.isZoomed()) return (float) (crosshairScale * BirdZoom.getMultiplier());
        return crosshairScale;
    }
    //?}
}
