package syric.alchemyplus.setup;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import syric.alchemyplus.AlchemyPlus;
import syric.alchemyplus.blocks.cauldron.AlchemicalCauldronBlock;
import syric.alchemyplus.blocks.OldAlchemicalCauldronBlock;
import syric.alchemyplus.blocks.slime.BounceSlimeBlock;
import syric.alchemyplus.blocks.slime.CrashPadBlock;
import syric.alchemyplus.blocks.slime.LaunchPadBlock;
import syric.alchemyplus.blocks.slime.RigidSlimeBlock;

import java.util.function.Supplier;

public class registerBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AlchemyPlus.MODID);



    //Cauldron
    public static final RegistryObject<Block> ALCHEMICAL_CAULDRON_OLD = register("alchemical_cauldron_old", () -> new OldAlchemicalCauldronBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.STONE).requiresCorrectToolForDrops().strength(2.0F).noOcclusion()));
    public static final RegistryObject<AlchemicalCauldronBlock> ALCHEMICAL_CAULDRON = register("alchemical_cauldron", () -> new AlchemicalCauldronBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.STONE).requiresCorrectToolForDrops().strength(2.0F).noOcclusion()));


    //Slimes
    public static final RegistryObject<Block> CRASH_PAD = register("crash_pad", () -> new CrashPadBlock(AbstractBlock.Properties.of(Material.CLAY, MaterialColor.GRASS).friction(0.8F).sound(SoundType.SLIME_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> RIGID_SLIME = register("rigid_slime", () -> new RigidSlimeBlock(AbstractBlock.Properties.of(Material.CLAY, MaterialColor.GRASS).friction(0.8F).sound(SoundType.SLIME_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> LAUNCH_PAD = register("launch_pad", () -> new LaunchPadBlock(AbstractBlock.Properties.of(Material.CLAY, MaterialColor.GRASS).friction(0.8F).sound(SoundType.SLIME_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> BOUNCE_SLIME = register("bounce_slime", () -> new BounceSlimeBlock(AbstractBlock.Properties.of(Material.CLAY, MaterialColor.GRASS).friction(0.8F).sound(SoundType.SLIME_BLOCK).noOcclusion()));



    static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> ret = registerNoItem(name, block);
        registerItems.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().tab(ItemGroup.TAB_BREWING)));
        return ret;
    }

}