package bluebird.birdzoom.mixin;

import bluebird.birdzoom.BirdZoom;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? if (> 1.20.1 || (fabric && > 1.19.4)) && (!forge || >= 1.21.2) {
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
//?} else {
/*import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.player.LocalPlayer;
*///?}

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @Inject(method = "onScroll", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isSpectator()Z"), cancellable = true)
    //? if >= 1.21.2 || < 1.20.2 {
    private void birdZoom$onScroll(CallbackInfo ci, @Local(ordinal = 0) int wheel) {
    //?} else {
    /*private void birdZoom$onScroll(CallbackInfo ci, @Local(ordinal = 2) int wheel) {
    *///?}
        if (BirdZoom.isZoomed()) {
            BirdZoom.handleScroll(wheel);
            ci.cancel();
        }
    }

    //? if (> 1.20.1 || (fabric && > 1.19.4)) && (!forge || >= 1.21.2) {
    @ModifyArgs(method = "turnPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;turn(DD)V"))
    private void birdZoom$onTurnPlayer(Args args) {
        if (BirdZoom.isZoomed()) {
            for (int i = 0; i < args.size(); i++) {
                args.set(i, (double) args.get(i) * BirdZoom.getMultiplier());
            }
        }
    }
    //?} else {
    /*@WrapOperation(method = "turnPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;turn(DD)V"))
    public void birdZoom$turnPlayer(LocalPlayer instance, double v1, double v2, Operation<Void> original) {
        if (BirdZoom.isZoomed()) {
            v1 *= BirdZoom.getMultiplier();
            v2 *= BirdZoom.getMultiplier();
        }
        original.call(instance, v1, v2);
    }
    *///?}
}
