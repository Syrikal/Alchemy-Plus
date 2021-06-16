package syric.alchemyplus.setup;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import syric.alchemyplus.AlchemyPlus;

public class registerItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AlchemyPlus.MODID);

    public static final RegistryObject<Item> ALCHEMICAL_FLASK = ITEMS.register("alchemical_flask", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> ALCHEMICAL_FLASK_FULL = ITEMS.register("alchemical_flask_full", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));


    static void register(IEventBus bus) {
        ITEMS.register(bus);
    }

}
