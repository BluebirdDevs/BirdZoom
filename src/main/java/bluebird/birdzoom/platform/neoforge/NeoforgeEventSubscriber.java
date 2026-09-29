package bluebird.birdzoom.platform.neoforge;

//? neoforge {

/*import bluebird.birdzoom.BirdZoom;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
//? if <= 1.20.4 {
/^import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;
^///?} else {
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
 //?}

//? if <= 1.20.4 {
/^@Mod.EventBusSubscriber
^///?} else {
@EventBusSubscriber
//?}
public class NeoforgeEventSubscriber {
    @SubscribeEvent
    //? if > 1.20.4 {
    public static void clientTickEvent(ClientTickEvent.Pre event) {
    //?} else {
    /^public static void clientTickEvent(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) return;
    ^///?}
        BirdZoom.onTick(Minecraft.getInstance());
    }
}
*///?}
