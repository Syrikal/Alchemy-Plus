package syric.alchemyplus.alchemy;

import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Substance {

    public String name;
    public Set<Ingredient> reactants;
    public Boolean fullCauldron;
    public Item itemEquivalent;

//Constructors
    public Substance(String nameIn, Set<Ingredient> reactants, Boolean fullCauldron, Item itemEquivalent) {
        this.name = nameIn;
        this.reactants = reactants;
        this.fullCauldron = fullCauldron;
        this.itemEquivalent = itemEquivalent;
    }

    public Substance(String nameIn, Ingredient[] reactants, Boolean fullCauldron, Item itemEquivalent) {
        this.name = nameIn;
        this.reactants = new HashSet<Ingredient>() {};
        this.reactants.addAll(Arrays.asList(reactants));
        this.fullCauldron = fullCauldron;
        this.itemEquivalent = itemEquivalent;
    }



    //Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Set<Ingredient> getReactants() {
        return reactants;
    }
    public void setReactants(Set<Ingredient> reactants) {
        this.reactants = reactants;
    }
    public Boolean getFullCauldron() {
        return fullCauldron;
    }
    public void setFullCauldron(Boolean fullCauldron) {
        this.fullCauldron = fullCauldron;
    }
}
