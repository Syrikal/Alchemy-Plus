package syric.alchemyplus.alchemy;

import net.minecraft.item.Item;
import net.minecraft.potion.Effect;

public class PotionSubstance extends Substance {
    public Effect effect;
    public int durLevel; //Right now it's 0 for basic, 1 for extended. I might make it a time later, for custom potions.
    public int powerLevel; //Same here, it's 0 for basic, 1 for level 2.

    public PotionSubstance(String nameIn, Ingredient[] reactants, Boolean fullCauldron, Item item, Effect effect, int durLevel, int powerLevel) {
        super(nameIn, reactants, fullCauldron, item);
        this.effect = effect;
        this.durLevel = durLevel;
        this.powerLevel = powerLevel;
    }

}