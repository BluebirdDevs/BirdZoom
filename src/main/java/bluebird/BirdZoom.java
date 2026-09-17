package bluebird;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BirdZoom implements ModInitializer {
	public static final String MOD_ID = "birdzoom";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(id("category"));
	private static KeyMapping zoom;

	private static final float DEFAULT_ZOOM_FOV = 3F;
	private static float zoomFOV = DEFAULT_ZOOM_FOV;

	private static double zoomSensMultiplier = 1 / DEFAULT_ZOOM_FOV;

	@Override
	public void onInitialize() {
		zoom = KeyMappingHelper.registerKeyMapping(new KeyMapping(
				"key.visualbarriers.toggle",
				InputConstants.KEY_V,
				CATEGORY
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (!isZoomed() && zoomFOV != DEFAULT_ZOOM_FOV) {
				zoomFOV = DEFAULT_ZOOM_FOV;
				zoomSensMultiplier = 1 / DEFAULT_ZOOM_FOV;
			}
		});
	}

	public static boolean isZoomed() {
		return zoom.isDown();
	}

	public static float getZoomFOV(float og) {
		return og / zoomFOV;
	}

	public static void handleScroll(int wheel) {
		if (wheel == 0) return;
		if (wheel > 0) {
			zoomFOV *= 1.1F;
		}
		else {
			zoomFOV *= .9F;
		}

		zoomFOV = Math.clamp(zoomFOV, 1, Minecraft.getInstance().options.fov().get());
		zoomSensMultiplier = 1 / zoomFOV;
	}

	public static double getSensMultiplier() {
		return zoomSensMultiplier;
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
