package syric.alchemyplus.blocks.slime;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BreakableBlock;
import net.minecraft.block.SlimeBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.block.ScaffoldingBlock;

public class CrashPadBlock extends BreakableBlock {

    public CrashPadBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    public void fallOn(World world, BlockPos pos, Entity entity, float num) {
        if (entity.isSuppressingBounce()) {
            super.fallOn(world, pos, entity, num);
        } else {
            entity.causeFallDamage(num, 0.0F);
        }
        if (!world.isClientSide) {
            world.destroyBlock(pos, true);
        }
    }
}
