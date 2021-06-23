package syric.alchemyplus.blocks.cauldron;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IClearable;
import net.minecraft.tileentity.*;
import net.minecraft.block.CampfireBlock;
import net.minecraft.world.World;
import syric.alchemyplus.setup.DebugPrint;
import syric.alchemyplus.setup.registerTileEntityTypes;

public class AlchemicalCauldronTileEntity extends TileEntity implements ITickableTileEntity, IClearable {
    public AlchemicalCauldronTileEntity() {
        super(registerTileEntityTypes.ALCHEMICAL_CAULDRON.get());
    }

    public void speak(PlayerEntity player, World world) {
        DebugPrint.print("tile entity exists", player, world);
    }

    @Override
    public void tick() {

    }


    @Override
    public void clearContent() {

    }


}
