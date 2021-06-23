package syric.alchemyplus.setup;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import syric.alchemyplus.AlchemyPlus;
import syric.alchemyplus.blocks.cauldron.AlchemicalCauldronTileEntity;

public class registerTileEntityTypes {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, AlchemyPlus.MODID);

    //Cauldron

    public static final RegistryObject<TileEntityType<AlchemicalCauldronTileEntity>> ALCHEMICAL_CAULDRON = TILE_ENTITIES.register("alchemical_cauldron", () -> {return TileEntityType.Builder.of(AlchemicalCauldronTileEntity::new, registerBlocks.ALCHEMICAL_CAULDRON.get()).build(null);});

    static void register(IEventBus bus) {
        TILE_ENTITIES.register(bus);
    }

}