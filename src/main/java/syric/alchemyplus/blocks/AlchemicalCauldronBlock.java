package syric.alchemyplus.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.block.CauldronBlock;
import syric.alchemyplus.AlchemyPlus;
import syric.alchemyplus.setup.registerItems;
import syric.alchemyplus.setup.registry;

import javax.annotation.Nullable;

public class AlchemicalCauldronBlock extends Block {
    public static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 3);
    private static final VoxelShape INSIDE = box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    protected static final VoxelShape SHAPE = VoxelShapes.join(VoxelShapes.block(), VoxelShapes.or(box(0.0D, 0.0D, 4.0D, 16.0D, 3.0D, 12.0D), box(4.0D, 0.0D, 0.0D, 12.0D, 3.0D, 16.0D), box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D), INSIDE), IBooleanFunction.ONLY_FIRST);

//    public AlchemicalCauldronBlock() {
//        super(Properties.of(Material.METAL).sound(SoundType.METAL).harvestLevel(3).strength(2F,2F));
////        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 0));
////        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, Integer.valueOf(0)));
//    }


    public AlchemicalCauldronBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, Integer.valueOf(0)));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }

    public VoxelShape getShape(BlockState blockstate, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    public VoxelShape getInteractionShape(BlockState blockstate, IBlockReader reader, BlockPos pos) {
        return INSIDE;
    }

//    public void entityInside(BlockState blockstate, World world, BlockPos pos, Entity entity) {
//        int i = blockstate.getValue(LEVEL);
//        String s = String.valueOf(i);
//        ITextComponent text = new StringTextComponent(s);
//        if (!world.isClientSide && entity instanceof PlayerEntity) {
//            entity.sendMessage(text, entity.getUUID());
//        }
//
////        float f = (float)pos.getY() + (6.0F + (float)(3 * i)) / 16.0F;
////        if (!world.isClientSide && entity.isOnFire() && i > 0 && entity.getY() <= (double)f) {
////            entity.clearFire();
////            this.setWaterLevel(world, pos, blockstate, i - 1);
////        }
//    }

//    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
//        ItemStack itemstack = player.getItemInHand(hand);
//
//        if (itemstack.isEmpty()) {
//            return ActionResultType.PASS;
//
//        } else {
//            int i = state.getValue(LEVEL);
//            Item item = itemstack.getItem();
//
//            if (item == Items.WATER_BUCKET) {
//                if (i < 3 && !world.isClientSide) {
//                        if (!player.abilities.instabuild) {
//                            player.setItemInHand(hand, new ItemStack(Items.BUCKET));
//                        }
//
//                        player.awardStat(Stats.FILL_CAULDRON);
//                        this.setWaterLevel(world, pos, state, 3);
//                        world.playSound((PlayerEntity) null, pos, SoundEvents.BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
//                        return ActionResultType.sidedSuccess(false);
//                }
//
//            } else if (item == registerItems.ALCHEMICAL_FLASK.get()) {
//                    if (i > 0 && !world.isClientSide) {
//                        player.setItemInHand(hand, new ItemStack(registerItems.ALCHEMICAL_FLASK_FULL.get()));
//                        this.setWaterLevel(world, pos, state, i-1);
//                        world.playSound((PlayerEntity) null, pos, SoundEvents.BOTTLE_FILL, SoundCategory.PLAYERS, 1.0F, 1.0F);
//                        return ActionResultType.sidedSuccess(false);
//                    }
//            } else {
//                return ActionResultType.PASS;
//            }
//        }
//        return ActionResultType.PASS;
//    }
//
//    public void setWaterLevel(World world, BlockPos pos, BlockState state, int level) {
//        world.setBlock(pos, state.setValue(LEVEL, Integer.valueOf(MathHelper.clamp(level, 0, 3))), 2);
//    }


    }
