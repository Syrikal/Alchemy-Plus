package syric.alchemyplus.setup;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SlimeBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import syric.alchemyplus.AlchemyPlus;
import syric.alchemyplus.blocks.AlchemicalCauldronBlock;
import syric.alchemyplus.blocks.slime.BounceSlimeBlock;
import syric.alchemyplus.blocks.slime.CrashPadBlock;
import syric.alchemyplus.blocks.slime.LaunchPadBlock;
import syric.alchemyplus.blocks.slime.RigidSlimeBlock;

import java.util.function.Supplier;

public class registerBlocks {

//    public static ItemGroup TAB_BLOCKS = new ItemGroup(AlchemyPlus.MODID) {
//        @Override
//        public ItemStack makeIcon() {
//            return new ItemStack(EXAMPLE_ITEM);
//        }
//    };

    //Cauldron
    public static final RegistryObject<Block> ALCHEMICAL_CAULDRON = register("alchemical_cauldron", () -> new AlchemicalCauldronBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.STONE).requiresCorrectToolForDrops().strength(2.0F).noOcclusion()));

    //Slimes
    public static final RegistryObject<Block> CRASH_PAD = register("crash_pad", () -> new CrashPadBlock(AbstractBlock.Properties.of(Material.CLAY, MaterialColor.GRASS).friction(0.8F).sound(SoundType.SLIME_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> RIGID_SLIME = register("rigid_slime", () -> new RigidSlimeBlock(AbstractBlock.Properties.of(Material.CLAY, MaterialColor.GRASS).friction(0.8F).sound(SoundType.SLIME_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> LAUNCH_PAD = register("launch_pad", () -> new LaunchPadBlock(AbstractBlock.Properties.of(Material.CLAY, MaterialColor.GRASS).friction(0.8F).sound(SoundType.SLIME_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> BOUNCE_SLIME = register("bounce_slime", () -> new BounceSlimeBlock(AbstractBlock.Properties.of(Material.CLAY, MaterialColor.GRASS).friction(0.8F).sound(SoundType.SLIME_BLOCK).noOcclusion()));



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