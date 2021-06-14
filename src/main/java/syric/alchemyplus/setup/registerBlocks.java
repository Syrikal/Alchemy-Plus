package syric.alchemyplus.setup;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import syric.alchemyplus.AlchemyPlus;
import syric.alchemyplus.blocks.AlchemicalCauldronBlock;

import java.util.function.Supplier;

public class registerBlocks {
//    public static ItemGroup TAB_BLOCKS = new ItemGroup(AlchemyPlus.MODID) {
//        @Override
//        public ItemStack makeIcon() {
//            return new ItemStack(EXAMPLE_ITEM);
//        }
//    };

    public static final RegistryObject<Block> ALCHEMICAL_CAULDRON = register("alchemical_cauldron", AlchemicalCauldronBlock::new);

    static void register() {}

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
        return registry.BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> ret = registerNoItem(name, block);
        registry.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().tab(ItemGroup.TAB_BREWING)));
        return ret;
    }

}