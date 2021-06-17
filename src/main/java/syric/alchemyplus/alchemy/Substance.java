package syric.alchemyplus.alchemy;

import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Substance {

    public String name;
    public Set<Item> recipe;
    public Item[] additives;
    public Substance[] results;
    public Set<Item> reactants;
    public Boolean fullCauldron;


    public Substance(String nameIn, Set<Item> recipe, Item[] additives, Substance[] results, Set<Item> reactants, Boolean fullCauldron) {
        this.name = nameIn;
        this.recipe = recipe;
        this.additives = additives;
        this.results = results;
        this.reactants = reactants;
        this.fullCauldron = fullCauldron;
    }

    public Substance(String nameIn, Item[] recipeIn, Item[] additives, Substance[] results, Item[] reactants, Boolean fullCauldron) {
        this.name = nameIn;
        this.recipe = new HashSet<Item>() {};
        this.recipe.addAll(Arrays.asList(recipeIn));
        this.additives = additives;
        this.results = results;
        this.reactants = new HashSet<Item>() {};
        this.reactants.addAll(Arrays.asList(reactants));
        this.fullCauldron = fullCauldron;
    }

}
