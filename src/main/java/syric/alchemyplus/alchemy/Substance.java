package syric.alchemyplus.alchemy;

import net.minecraft.item.Item;

public class Substance {

    public final Item[] recipe;
    public final Item[] additives;
    public final Substance[] results;
    public final Item[] reactants;
    public final Boolean fullCauldron;



    public Substance(Item[] recipe, Item[] additives, Substance[] results, Item[] reactants, Boolean fullCauldron) {
        this.recipe = recipe;
        this.additives = additives;
        this.results = results;
        this.reactants = reactants;
        this.fullCauldron = fullCauldron;
    }


}
