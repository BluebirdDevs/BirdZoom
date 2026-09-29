package bluebird.birdzoom.mixin.old;

import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;

//? if < 26.1 {
/*import bluebird.birdzoom.BirdZoom;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @ModifyReturnValue(method = "getFov", at = @At("RETURN"))
    //? if >= 1.21.2 {
    private float birdZoom$getFov(float original) {
    //?} else {
    /^private double birdZoom$getFov(double original) {
    ^///?}
        if (BirdZoom.isZoomed()) {
            return BirdZoom.getZoomFOV(/^? if < 1.21.2 {^/ /^(float) ^//^?}^/ original);
        }
        return original;
    }

    //? if >= 1.21.9 {
    @ModifyArg(method = "getProjectionMatrixForCulling", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F"), index = 1)
    //?} else if > 1.21.4 {
    /^@ModifyArg(method = "renderLevel", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F", ordinal = 1), index = 1)
    ^///?} else if >= 1.21.2 {
    /^@ModifyArg(method = "renderLevel", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F", ordinal = 0), index = 1)
    ^///?} else {
    /^@ModifyArg(method = "renderLevel", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(DD)D"), index = 1)
    ^///?}
    //? >= 1.21.2 {
    private float birdZoom$createProjectionMatrixForCulling(float fovForCulling) {
    //?} else {
    /^private double birdZoom$createProjectionMatrixForCulling(double fovForCulling) {
    ^///?}
        if (BirdZoom.isZoomed()) {
            return Math.max(BirdZoom.getZoomFOV(this.minecraft.options.fov().get()), 2F);
        }
        return fovForCulling;
    }
}
*///?} else {
@Mixin(GameRenderer.class)
public class GameRendererMixin {}
//?}