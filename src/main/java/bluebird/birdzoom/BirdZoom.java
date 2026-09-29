package bluebird.birdzoom;

import bluebird.birdzoom.platform.Platform;
import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
//? if >= 1.21.9 {
import net.minecraft.resources.Identifier;
//?}

import net.minecraft.util.Mth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//? fabric {
import bluebird.birdzoom.platform.fabric.FabricPlatform;
//?} neoforge {
/*import bluebird.birdzoom.platform.neoforge.NeoforgePlatform;
 *///?} forge {
/*import bluebird.birdzoom.platform.forge.ForgePlatform;
 *///?}

public class BirdZoom {
	public static final String MOD_ID = "birdzoom";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static final Platform PLATFORM = createPlatformInstance();

	//? if >= 1.21.9 {
	private static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(id("category"));
	//?}

	private static final float DEFAULT_ZOOM_FOV = 3F;
	private static float zoomFOV = DEFAULT_ZOOM_FOV;

	private static boolean fovWasChanged = false;
	private static boolean wasZoomed = false;

	public static final KeyMapping zoom = new KeyMapping(
			"key.birdzoom.activate",
			InputConstants.KEY_V,
	//? if >= 1.21.9 {
			CATEGORY
	//?} else {
			/*"key.category.birdzoom.category"
	*///?}
	);

	public static void onTick(Minecraft client) {
		if (!BirdZoom.isZoomed() && zoomFOV != DEFAULT_ZOOM_FOV) {
			zoomFOV = DEFAULT_ZOOM_FOV;
		}
		fovWasChanged = false;
		if (isZoomed() != wasZoomed) {
			wasZoomed = isZoomed();
			fovWasChanged = true;
		}
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
		fovWasChanged = true;

		zoomFOV = Mth.clamp(zoomFOV, 1, 500);
	}

	public static double getMultiplier() {
		return 1 / zoomFOV;
	}

	public static boolean fovChanged() {
		return fovWasChanged;
	}

	//? if >= 1.21.9 {
	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
	//?}

	static Platform platform() {
		return PLATFORM;
	}

	private static Platform createPlatformInstance() {
		//? fabric {
		return new FabricPlatform();
		//?} neoforge {
		/*return new NeoforgePlatform();
		 *///?} forge {
		/*return new ForgePlatform();
		 *///?}
	}
}
