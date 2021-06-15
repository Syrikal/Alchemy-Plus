package syric.alchemyplus.blocks.slime;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BreakableBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class BounceSlimeBlock extends BreakableBlock {

    public BounceSlimeBlock(AbstractBlock.Properties properties) {super(properties); }

    public void stepOn(World world, BlockPos pos, Entity entity) {
        Vector3d vector3d = entity.getDeltaMovement();
        if (vector3d.y <= 0.0D && !entity.isSteppingCarefully()) {
            entity.setDeltaMovement(vector3d.x, 2.0D, vector3d.z);
        }
        super.stepOn(world, pos, entity);
    }

}


