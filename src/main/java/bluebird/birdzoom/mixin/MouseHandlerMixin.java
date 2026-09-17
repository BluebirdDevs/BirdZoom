package bluebird.birdzoom.mixin;

import bluebird.birdzoom.BirdZoom;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Environment(EnvType.CLIENT)
@Mixin(MouseHandler.class)
public class MouseHandlerMixin {
    @Inject(method = "onScroll", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isSpectator()Z"), cancellable = true)
    private static void birdZoom$onScroll(CallbackInfo ci, @Local(name = "wheel") int wheel) {
        if (BirdZoom.isZoomed()) {
            BirdZoom.handleScroll(wheel);
            ci.cancel();
        }
    }
    @ModifyArgs(method = "turnPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;turn(DD)V"))
    private static void birdZoom$onTurnPlayer(Args args) {
        if (BirdZoom.isZoomed()) {
            for (int i = 0; i < args.size(); i++) {
                args.set(i, (double) args.get(i) * BirdZoom.getMultiplier());
            }
        }
    }
}
