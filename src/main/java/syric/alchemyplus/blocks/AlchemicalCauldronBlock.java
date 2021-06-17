package syric.alchemyplus.blocks;

import io.netty.handler.codec.memcache.binary.DefaultBinaryMemcacheRequest;
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
import syric.alchemyplus.alchemy.IngredientsDict;
import syric.alchemyplus.alchemy.Substance;
//import syric.alchemyplus.setup.DebugPrint;
import syric.alchemyplus.setup.DebugPrint;
import syric.alchemyplus.setup.registerBlocks;
import syric.alchemyplus.setup.registerItems;
import syric.alchemyplus.setup.registry;
import syric.alchemyplus.alchemy.SubstanceList;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class AlchemicalCauldronBlock extends Block {
    public static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 3);
    private static final VoxelShape INSIDE = box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    protected static final VoxelShape SHAPE = VoxelShapes.join(VoxelShapes.block(), VoxelShapes.or(box(0.0D, 0.0D, 4.0D, 16.0D, 3.0D, 12.0D), box(4.0D, 0.0D, 0.0D, 12.0D, 3.0D, 16.0D), box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D), INSIDE), IBooleanFunction.ONLY_FIRST);
    public boolean slimed;


    public AlchemicalCauldronBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, Integer.valueOf(0)));
        this.slimed = false;
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

    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        ItemStack itemstack = player.getItemInHand(hand);

//        DebugPrint.print("cauldron activated with: " + itemstack.toString() + ", client world: " + world.isClientSide, player, world);
        DebugPrint.print(String.valueOf(slimed), player, world);


        if (itemstack.isEmpty()) {
//            DebugPrint.print("hand empty", player, world);
            return ActionResultType.PASS;

        } else {
            int i = state.getValue(LEVEL);
            Item item = itemstack.getItem();

            if (item == Items.WATER_BUCKET) {
//                DebugPrint.print("water bucket recognized", player, world);
                if (i < 3 && !world.isClientSide) {
                        if (!player.abilities.instabuild) {
                            player.setItemInHand(hand, new ItemStack(Items.BUCKET));
                        }
                        player.awardStat(Stats.FILL_CAULDRON);
                        this.setWaterLevel(world, pos, state, 3);
//                        DebugPrint.print("cauldron filled", player, world);
                        slimed = false;
                        DebugPrint.print("unslimed", player, world);
                        world.playSound((PlayerEntity) null, pos, SoundEvents.BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
                return ActionResultType.sidedSuccess(world.isClientSide);

            } else if (item == registerItems.ALCHEMICAL_FLASK.get()) {
//                DebugPrint.print("alchemical flask recognized", player, world);
                if (i > 0 && !world.isClientSide) {
                    if (slimed) {
                        DebugPrint.print("slimed!", player, world);
                        if (itemstack.isEmpty()) {
                            player.setItemInHand(hand, new ItemStack(Items.SLIME_BLOCK));
                        } else if (!player.inventory.add(new ItemStack(Items.SLIME_BLOCK))) {
                            player.drop(new ItemStack(Items.SLIME_BLOCK), false);
                        }
                        this.setWaterLevel(world, pos, state, 0);
                        DebugPrint.print(SubstanceList.list.get(0).name, player, world);
                        DebugPrint.print(IngredientsDict.dict.get(Items.SLIME_BALL).id, player, world);

                        slimed = false;
                        DebugPrint.print("unslimed", player, world);

                    } else {
                        DebugPrint.print("not slimed", player, world);
                        if (!player.abilities.instabuild) {
                            itemstack.shrink(1);
                        }
                        if (itemstack.isEmpty()) {
                            player.setItemInHand(hand, new ItemStack(registerItems.ALCHEMICAL_FLASK_FULL.get()));
                        } else if (!player.inventory.add(new ItemStack(registerItems.ALCHEMICAL_FLASK_FULL.get()))) {
                            player.drop(new ItemStack(registerItems.ALCHEMICAL_FLASK_FULL.get()), false);
                        }
                    }
                    this.setWaterLevel(world, pos, state, i - 1);
                    world.playSound((PlayerEntity) null, pos, SoundEvents.BOTTLE_FILL, SoundCategory.PLAYERS, 1.0F, 1.0F);
//                    DebugPrint.print("flask filled", player, world);
                }
                return ActionResultType.sidedSuccess(world.isClientSide);

            } else if (item == Items.SLIME_BALL) {
//                DebugPrint.print("slime ball recognized", player, world);
                if (i > 0 && !world.isClientSide) {
                    slimed = true;
                    itemstack.shrink(1);
//                    DebugPrint.print("slime added to cauldron", player, world);
                    world.playSound((PlayerEntity) null, pos, SoundEvents.GENERIC_SPLASH, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
                return ActionResultType.sidedSuccess(world.isClientSide);

            } else {
//                DebugPrint.print("item not recognized, passing", player, world);
                return ActionResultType.PASS;
            }
        }
    }

    public void setWaterLevel(World world, BlockPos pos, BlockState state, int level) {
        world.setBlock(pos, state.setValue(LEVEL, Integer.valueOf(MathHelper.clamp(level, 0, 3))), 2);
    }


    }
