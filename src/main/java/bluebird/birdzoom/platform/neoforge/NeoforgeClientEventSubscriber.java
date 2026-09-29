package bluebird.birdzoom.platform.neoforge;

//? neoforge {

/*import bluebird.birdzoom.BirdZoom;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
//? if <= 1.20.3 {
/^import net.neoforged.fml.common.Mod;
^///?} else {
import net.neoforged.fml.common.EventBusSubscriber;
//?}
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

//? if <= 1.20.3 {
/^@Mod.EventBusSubscriber(modid = BirdZoom.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
 ^///?} else if <= 1.21.2 {
/^@EventBusSubscriber(modid = BirdZoom.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
^///?} else {
@EventBusSubscriber(modid = BirdZoom.MOD_ID, value = Dist.CLIENT)
//?}
public class NeoforgeClientEventSubscriber {
	@SubscribeEvent
	public static void registerKeybinds(RegisterKeyMappingsEvent event) {
		event.register(BirdZoom.zoom);
	}
}
*///?}
