package bluebird.birdzoom.platform.forge;

//? forge {

/*import bluebird.birdzoom.BirdZoom;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
//? if <= 1.21.5 {
/^import net.minecraftforge.eventbus.api.SubscribeEvent;
 ^///?} else {
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
//?}

//? if < 1.21.9 {
/^import net.minecraftforge.client.event.RegisterKeyMappingsEvent;

@Mod.EventBusSubscriber(modid = BirdZoom.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
^///?}
public class ForgeClientEventSubscriber {
    //? if < 1.21.9 {
    /^@SubscribeEvent
    public static void registerKeybinds(RegisterKeyMappingsEvent event) {
        event.register(BirdZoom.zoom);
    }
    ^///?}
}
*///?}
