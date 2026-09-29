package bluebird.birdzoom.platform.fabric;

//? fabric {

import bluebird.birdzoom.BirdZoom;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
//? >= 26.1 {
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
//?} else {
/*import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
*///?}

@Entrypoint("client")
public class FabricClientEntrypoint implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		//? >= 26.1 {
		KeyMappingHelper.registerKeyMapping(BirdZoom.zoom);
		//?} else {
		/*KeyBindingHelper.registerKeyBinding(BirdZoom.zoom);
		*///?}
		ClientTickEvents.START_CLIENT_TICK.register(BirdZoom::onTick);
	}

}
//?}
