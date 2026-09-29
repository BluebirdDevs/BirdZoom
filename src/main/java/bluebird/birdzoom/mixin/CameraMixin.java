package bluebird.birdzoom.mixin;

import bluebird.birdzoom.BirdZoom;
import net.minecraft.client.Camera;
//? if >= 26.1 {
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
//?}
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Camera.class)
public class CameraMixin {
    //? if >= 26.1 {
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
        return BirdZoom.isZoomed() ? BirdZoom.getZoomFOV(fov) : fov;
    }
    //?} else {
    /*@Redirect(method = "getNearPlane", at = @At(value = "INVOKE", target = "Ljava/lang/Math;tan(D)D"))
    private double birdZoom$getNearPlane(double a) {
        if (BirdZoom.isZoomed()) {
            float fov = (float) (a * 2.0 / ((float)Math.PI / 180F));
            return (BirdZoom.getZoomFOV(fov) * ((float)Math.PI / 180F)) / (double)2.0F;
        }
        return a;
    }
    *///?}
}
