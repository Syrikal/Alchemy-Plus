package syric.alchemyplus.alchemy;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.Dictionary;
import java.util.Hashtable;

public class IngredientsDict {
    public static Dictionary<Item,Ingredient> dict = new Hashtable<Item, Ingredient>();

    public IngredientsDict() {

        add(Items.SLIME_BALL, "slime_ball", 0,false, new Item[]{});
        add(Items.GUNPOWDER, "gunpowder", 0,false, new Item[]{});
        add(Items.WATER_BUCKET, "water_bucket", 3,true, new Item[]{});

    }

    private static void add(Item item, String id, int vol, boolean special, Item[] reactants) {
        Ingredient ingredient = new Ingredient(id, vol, special, reactants);
        dict.put(item, ingredient);
    }

}
