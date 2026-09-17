package bluebird.birdzoom.mixin;

import bluebird.birdzoom.BirdZoom;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Environment(EnvType.CLIENT)
@Mixin(Camera.class)
public class CameraMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @ModifyReturnValue(method = "calculateFov", at = @At("RETURN"))
    private float birdZoom$getFov(float original) {
        if (BirdZoom.isZoomed()) {
            return BirdZoom.getZoomFOV(original);
        }
        return original;
    }

    @ModifyReturnValue(method = "calculateHudFov", at = @At("RETURN"))
    private float birdZoom$calculateHudFov(float original) {
        if (BirdZoom.isZoomed()) return BirdZoom.getZoomFOV(original);
        return original;
    }

    @ModifyArg(method = "createProjectionMatrixForCulling", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F"), index = 1)
    private float birdZoom$createProjectionMatrixForCulling(float fovForCulling) {
        if (BirdZoom.isZoomed()) {
            return Math.max(BirdZoom.getZoomFOV(this.minecraft.options.fov().get()), 2F);
        }
        return fovForCulling;
    }

    @ModifyVariable(method = "getNearPlane", at = @At(value = "HEAD"), argsOnly = true, ordinal = 0)
    private float birdZoom$getNearPlane(float fov) {
        if (BirdZoom.isZoomed()) {
            return BirdZoom.getZoomFOV(fov);
        }
        return fov;
    }
}
