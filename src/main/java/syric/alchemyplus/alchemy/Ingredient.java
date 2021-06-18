package syric.alchemyplus.alchemy;

import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Ingredient {

    public String id;
    public int volume;
    public boolean special;
    public Set<Ingredient> reactants;

    public Ingredient(String id, int volume, boolean special, Set<Ingredient> reactantsIn) {
        this.id = id;
        this.volume = volume;
        this.special = special;
        this.reactants = reactantsIn;
    }

    public Ingredient(String id, int volume, boolean special, Ingredient[] reactantsIn) {
        this.id = id;
        this.volume = volume;
        this.special = special;
        this.reactants = new HashSet<Ingredient>() {};
        this.reactants.addAll(Arrays.asList(reactantsIn));
    }

}
