package syric.alchemyplus.blocks.cauldron;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CauldronBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class AlchemicalCauldronBlock extends Block {
    public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL_CAULDRON;
    private static final VoxelShape INSIDE = box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    protected static final VoxelShape SHAPE = VoxelShapes.join(VoxelShapes.block(), VoxelShapes.or(box(0.0D, 0.0D, 4.0D, 16.0D, 3.0D, 12.0D), box(4.0D, 0.0D, 0.0D, 12.0D, 3.0D, 16.0D), box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D), INSIDE), IBooleanFunction.ONLY_FIRST);

    //Constructor
    public AlchemicalCauldronBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 0));
    }


    //Some tile entity stuff
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new AlchemicalCauldronTileEntity();
        //TODO make this return a tile entity
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState state, World world, BlockPos pos, BlockState otherState, boolean isMoving) {
        if (!state.is(otherState.getBlock())) {
            TileEntity tileEntity = world.getBlockEntity(pos);
            if (tileEntity instanceof IInventory) {
                //TODO fix that if statement
                //Here: break the tile entity
                world.updateNeighbourForOutputSignal(pos, this);
                super.onRemove(state, world, pos, otherState, isMoving);
            }
        }
    }

    //When block is used:
    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult ray) {
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
        }
        this.interactWith(state, world, pos, player, hand);
        return ActionResultType.CONSUME;
    }

    private void interactWith(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand) {
        TileEntity tileEntity = world.getBlockEntity(pos);
        if (tileEntity instanceof AlchemicalCauldronTileEntity) {
            AlchemicalCauldronTileEntity alcTile = (AlchemicalCauldronTileEntity) tileEntity;
            alcTile.speak(player, world);
        }
    }

    //Some necessary stuff to make shape and blockstates work
    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState blockstate, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
    @SuppressWarnings("deprecation")
    public VoxelShape getInteractionShape(BlockState blockstate, IBlockReader reader, BlockPos pos) {
        return INSIDE;
    }


}
