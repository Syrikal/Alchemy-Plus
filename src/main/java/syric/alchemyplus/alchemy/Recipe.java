package syric.alchemyplus.alchemy;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Recipe {
    public HashMap<Substance, Integer> predicateSubstances = new HashMap<>();
    public ArrayList<Ingredient> predicateIngredients = new ArrayList<>();
    public Pair<Substance, Integer> output;
    public boolean flexibleAmount;


    //Constructor with final variable types
    public Recipe(HashMap<Substance, Integer> subs, ArrayList<Ingredient> ings, Pair<Substance, Integer> output, boolean flexible) {
        predicateSubstances = subs;
        predicateIngredients = ings;
        this.output = output;
        flexibleAmount = flexible;
    }

    //Constructor that makes the SubstanceQuantityPairs for you
    public Recipe(Substance[] subs, int[] amounts, Ingredient[] ings, Substance out, int outAmount, boolean flexible) {
        if (subs.length != amounts.length) {
            throw new IllegalArgumentException("Recipe substances and amounts lists are different sizes");
        }
        for (int i=0; i <subs.length; i++) {
            predicateSubstances.put(subs[i],amounts[i]);
        }
        predicateIngredients.addAll(Arrays.asList(ings));
        output = new Pair<Substance, Integer>(out, outAmount);
        flexibleAmount = flexible;
    }

    //Method that checks if a recipe has been met, given a set of substances and ingredients
    public boolean isMet(HashMap<Substance, Integer> cauldronSubstances, ArrayList<Ingredient> cauldronIngredients) {
        boolean ret = true;
        for (Substance substance : predicateSubstances.keySet()) {
            if(cauldronSubstances.get(substance) != null || cauldronSubstances.get(substance) < predicateSubstances.get(substance)) {
                ret = false;
            }
        }
        if (!cauldronIngredients.containsAll(predicateIngredients)) {
            ret = false;
        }
        return ret;
    }


}
