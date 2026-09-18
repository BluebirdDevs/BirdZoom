package bluebird.birdzoom.mixin;

import bluebird.birdzoom.BirdZoom;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @ModifyReturnValue(method = "getFov", at = @At("RETURN"))
    private float birdZoom$getFov(float original) {
        if (BirdZoom.isZoomed()) {
            return BirdZoom.getZoomFOV(original);
        }
        return original;
    }

    @ModifyArg(method = "getProjectionMatrixForCulling", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F"), index = 1)
    private float birdZoom$createProjectionMatrixForCulling(float fovForCulling) {
        if (BirdZoom.isZoomed()) {
            return Math.max(BirdZoom.getZoomFOV(this.minecraft.options.fov().get()), 2F);
        }
        return fovForCulling;
    }
}
