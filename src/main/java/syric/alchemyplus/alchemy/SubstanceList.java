package syric.alchemyplus.alchemy;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import syric.alchemyplus.alchemy.Substance;

public class SubstanceList {
    public static ArrayList<Substance> list = new ArrayList<Substance>();

    public SubstanceList() {
        add("crash_pad", new Item[]{Items.SLIME_BALL, Items.PHANTOM_MEMBRANE}, new Item[]{}, new Substance[]{}, new Item[]{}, true);
    }

    private static void add(String nameIn, Item[] recipeIn, Item[] additives, Substance[] results, Item[] reactants, Boolean fullCauldron) {
        Substance substance = new Substance(nameIn, recipeIn, additives, results, reactants, fullCauldron);
        list.add(substance);
    }

}