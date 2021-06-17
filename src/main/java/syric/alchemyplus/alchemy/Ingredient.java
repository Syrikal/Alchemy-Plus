package syric.alchemyplus.alchemy;

import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Ingredient {

    public String id;
    public int volume;
    public boolean special;
    public Set<Item> reactants;

    public Ingredient(String id, int volume, boolean special, Set<Item> reactantsIn) {
        this.id = id;
        this.volume = volume;
        this.special = special;
        this.reactants = reactantsIn;
    }

    public Ingredient(String id, int volume, boolean special, Item[] reactantsIn) {
        this.id = id;
        this.volume = volume;
        this.special = special;
        this.reactants = new HashSet<Item>() {};
        this.reactants.addAll(Arrays.asList(reactantsIn));
    }

}
