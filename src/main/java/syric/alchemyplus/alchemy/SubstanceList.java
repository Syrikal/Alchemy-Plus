package syric.alchemyplus.alchemy;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import syric.alchemyplus.alchemy.Substance;
import syric.alchemyplus.setup.registerBlocks;
import syric.alchemyplus.setup.registerItems;

public class SubstanceList {
    public static ArrayList<Substance> list = new ArrayList<Substance>();

    public static Substance water = new Substance("water", new Ingredient[]{}, false, null);
    public static Substance crash_pad = new Substance("crash_pad", new Ingredient[]{}, true, registerBlocks.CRASH_PAD.get().asItem());

    public SubstanceList() {
        initialize();
    }

    public static void initialize() {
        list.add(water);
        list.add(crash_pad);
    }

    //For getting ingredients
    private static Ingredient get(Item item) {
        return IngredientsDict.get(item);
    }

}