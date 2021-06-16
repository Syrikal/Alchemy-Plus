package syric.alchemyplus.setup;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class DebugPrint {

    public static void print(String input, PlayerEntity player, World world) {
        StringTextComponent text = new StringTextComponent(input);
        if (!world.isClientSide) {
            player.sendMessage(text, player.getUUID());
        }
    }
}
