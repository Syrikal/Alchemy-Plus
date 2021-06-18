package syric.alchemyplus.alchemy;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.ArrayList;

public class RecipeList {
    public static ArrayList<Recipe> list = new ArrayList<Recipe>();

    public static Recipe crash_pad = new Recipe(new Substance[]{SubstanceList.water}, new int[]{1}, new Ingredient[]{get(Items.SLIME_BALL), get(Items.PHANTOM_MEMBRANE)}, SubstanceList.crash_pad, 1, true);

    public RecipeList() {
        initialize();
    }

    public static void initialize() {
        list.add(crash_pad);
    }

    //For getting ingredients
    private static Ingredient get(Item item) {
        return IngredientsDict.get(item);
    }

}
