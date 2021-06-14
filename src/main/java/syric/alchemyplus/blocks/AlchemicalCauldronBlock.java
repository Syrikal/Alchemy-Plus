package syric.alchemyplus.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class AlchemicalCauldronBlock extends Block {
//    public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL_CAULDRON;
    private static final VoxelShape INSIDE = box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    protected static final VoxelShape SHAPE = VoxelShapes.join(VoxelShapes.block(), VoxelShapes.or(box(0.0D, 0.0D, 4.0D, 16.0D, 3.0D, 12.0D), box(4.0D, 0.0D, 0.0D, 12.0D, 3.0D, 16.0D), box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D), INSIDE), IBooleanFunction.ONLY_FIRST);

    public AlchemicalCauldronBlock() {
        super(Properties.of(Material.METAL).sound(SoundType.METAL).harvestLevel(3).strength(1.5F,6F));
//        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, Integer.valueOf(0)));
    }

    public VoxelShape getShape(BlockState blockstate, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    public VoxelShape getInteractionShape(BlockState blockstate, IBlockReader reader, BlockPos pos) {
        return INSIDE;
    }

//    public void entityInside(BlockState blockstate, World world, BlockPos pos, Entity entity) {
//        int i = blockstate.getValue(LEVEL);
//        float f = (float)pos.getY() + (6.0F + (float)(3 * i)) / 16.0F;
//        if (!world.isClientSide && entity.isOnFire() && i > 0 && entity.getY() <= (double)f) {
//            entity.clearFire();
//            this.setWaterLevel(world, pos, blockstate, i - 1);
//        }
//    }


}
