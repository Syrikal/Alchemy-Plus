package syric.alchemyplus.setup;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class registry {


    public static void register() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        registerItems.register(modEventBus);
        registerBlocks.register(modEventBus);
        registerTileEntityTypes.register(modEventBus);
    }

}