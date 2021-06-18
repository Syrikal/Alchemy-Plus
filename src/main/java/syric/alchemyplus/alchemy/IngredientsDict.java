package syric.alchemyplus.alchemy;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.Dictionary;
import java.util.Hashtable;

public class IngredientsDict {
    public static Dictionary<Item,Ingredient> dict = new Hashtable<Item, Ingredient>();

    public IngredientsDict() {
        initialize();
    }

    public static void initialize() {
        add(Items.SLIME_BALL, "slime_ball", 0,false, new Ingredient[]{});
        add(Items.GUNPOWDER, "gunpowder", 0,false, new Ingredient[]{});
        add(Items.WATER_BUCKET, "water_bucket", 3,true, new Ingredient[]{});
        add(Items.PHANTOM_MEMBRANE, "phantom_membrane", 0, false, new Ingredient[]{});
    }

    private static void add(Item item, String id, int vol, boolean special, Ingredient[] reactants) {
        Ingredient ingredient = new Ingredient(id, vol, special, reactants);
        dict.put(item, ingredient);
    }

    public static Ingredient get(Item item) {
        return dict.get(item);
    }

}
