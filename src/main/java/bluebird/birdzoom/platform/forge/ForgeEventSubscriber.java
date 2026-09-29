package bluebird.birdzoom.platform.forge;

//? forge {
/*//? if >= 1.21.9 {
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
//?}
import net.minecraftforge.event.TickEvent;
import bluebird.birdzoom.BirdZoom;
//? if <= 1.21.5 {
/^import net.minecraftforge.eventbus.api.SubscribeEvent;
^///?} else {
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
//?}
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BirdZoom.MOD_ID)
public class ForgeEventSubscriber {
    @SubscribeEvent
    //? if > 1.20.1 {
    public static void clientTickEvent(TickEvent.ClientTickEvent.Pre event) {
    //?} else {
    /^public static void clientTickEvent(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) return;
    ^///?}
        BirdZoom.onTick(Minecraft.getInstance());
    }

    //? if >= 1.21.9 {
    @SubscribeEvent
    public static void registerKeybinds(RegisterKeyMappingsEvent event) {
        event.register(BirdZoom.zoom);
    }
    //?}
}
*///?}
