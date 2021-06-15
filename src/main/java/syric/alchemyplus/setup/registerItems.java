package syric.alchemyplus.setup;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import syric.alchemyplus.AlchemyPlus;

public class registerItems {
//    public static ItemGroup TAB_ITEMS = new ItemGroup(AlchemyPlus.MODID) {
//        @Override
//        public ItemStack makeIcon() {
//            return new ItemStack(EXAMPLE_ITEM);
//        }
//    };
    public static final RegistryObject<Item> ALCHEMICAL_FLASK = registry.ITEMS.register("alchemical_flask", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));

    static void register() {}

}
