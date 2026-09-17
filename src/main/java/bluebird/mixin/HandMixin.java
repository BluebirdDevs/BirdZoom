package bluebird.mixin;

import bluebird.BirdZoom;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.FirstPersonHandsAndItemsRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.level.PlayerRenderState;
import net.minecraft.world.entity.HumanoidArm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(FirstPersonHandsAndItemsRenderer.class)
public class HandMixin {
//    @Inject(method = "renderPlayerArm", at = @At("HEAD"), cancellable = true)
//    public void birdzoom$hideHand(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, float inverseArmHeight, float attackValue, HumanoidArm arm, PlayerRenderState playerState, CallbackInfo ci) {
//        if (BirdZoom.isZoomed()) ci.cancel();
//    }
}
