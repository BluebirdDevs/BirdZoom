package bluebird.mixin;

import bluebird.BirdZoom;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Environment(EnvType.CLIENT)
@Mixin(Camera.class)
public class CameraMixin {
    @ModifyReturnValue(method = "calculateFov", at = @At("RETURN"))
    private float getFov(float original) {
        if (BirdZoom.isZoomed()) return BirdZoom.getZoomFOV(original);
        return original;
    }

    @ModifyReturnValue(method = "calculateHudFov", at = @At("RETURN"))
    private float calculateHudFov(float original) {
        if (BirdZoom.isZoomed()) return BirdZoom.getZoomFOV(original);
        return original;
    }
}
