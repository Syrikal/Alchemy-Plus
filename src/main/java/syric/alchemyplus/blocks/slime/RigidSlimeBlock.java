package syric.alchemyplus.blocks.slime;

import net.minecraft.block.BreakableBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RigidSlimeBlock extends BreakableBlock {

    public RigidSlimeBlock(Properties properties) {
        super(properties);
    }

    public void fallOn(World world, BlockPos pos, Entity entity, float num) {
        if (entity.isSuppressingBounce()) {
            super.fallOn(world, pos, entity, num);
        } else {
            entity.causeFallDamage(num, 0.0F);
        }
    }

}
