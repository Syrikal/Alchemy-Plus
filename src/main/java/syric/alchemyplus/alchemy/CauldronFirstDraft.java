package syric.alchemyplus.alchemy;

import javafx.util.Pair;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import syric.alchemyplus.setup.DebugPrint;

import java.util.ArrayList;
import java.util.HashMap;

public class CauldronFirstDraft {
    //Variables
    private HashMap<Substance, Integer> CauldronSubstances;
    private ArrayList<Ingredient> CauldronIngredients;
    //private Aspect aspect; TODO aspects:
    //sounds? particles? based on aspect?
    private int fullness;
    //some stuff for activity, brewtime, etc?
    private World world;
    private PlayerEntity player;


    //When Item Applied
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();
        this.world = world;
        this.player = player;


        //If the item is an ingredient:
        if (IngredientsDict.get(item) != null) {
            //If the item's an ingredient, we take it away
            Ingredient ingredient = IngredientsDict.get(item);
            removeItem(player, itemstack); //Takes away one item
            //TODO play a sound; particles?
            if (ingredient.special) { //If ingredient is special
                //handle special ingredients- water bucket, potions, full alchemical flasks, coal, etc.
                //Potentially add substances directly with addSubstance()
            } else {
                //First, add the ingredient to the pot and handle ingredient variables: volume, reactions, etc. Overflow if necessary.
                //This is where you handle stuff like brewtime and whatnot. Make the cauldron bubble when you throw something in.
                //Stuff like that.
                //TODO ingredient variables
                //CHECK OTHER INGREDIENTS' REACTIONS
                //CHECK SUBSTANCES' REACTIONS
                //Second, check the recipes.
                checkRecipes();

            }
        }

        //Item is not ingredient.
        // Next, check if the item is a container, used to extract substances (bucket, bottle, flask, throwable shell)
        else if (false) { //if item is a container
            //TODO extract();
        }

        //Item is not an ingredient or a container.
        //
        else {

        }

        return ActionResultType.SUCCESS;
    }


    //CheckRecipes
    //This function checks if conditions are met for any recipes. If they are, it applies those recipes.
    //If it adds new substances, the addSubstances() function automatically runs checkRecipes again.
    private void checkRecipes() {
        DebugPrint.print("Checking Recipes", player, world);
        for (Recipe recipe : RecipeList.list) {
            if (recipe.isMet(CauldronSubstances, CauldronIngredients)) {

                //First, flexible recipes: these output as much substance as is available of their input.
                if (recipe.flexibleAmount) {
                    if (recipe.predicateSubstances.size() != 1) {
                        //Flexible recipes may only have one predicate substance. Throws exception otherwise.
                        throw new IllegalArgumentException("Error: flexible recipe for " + recipe.output.getKey().name + " has multiple predicates");
                    } else {
                        int amount = CauldronSubstances.get(recipe.predicateSubstances.keySet());
                        Pair<Substance, Integer> subToAdd = new Pair<Substance, Integer>(recipe.output.getKey(), amount);

                        //Removing the inputs
                        for (Substance substance : recipe.predicateSubstances.keySet()) {
                            boolean success = CauldronSubstances.remove(substance, amount);
                            if (!success) { //i.e. there wasn't as much predicate as we thought when we calculated the amount
                                throw new ArithmeticException("Unexpected amount of predicate present");
                            }
                        }
                        for (Ingredient ingredient : recipe.predicateIngredients) {
                            boolean success = CauldronIngredients.remove(ingredient);
                            if (!success) { //i.e. an ingredient was missing
                                throw new ArithmeticException("Failed to remove ingredients");
                            }
                        }
                        addSubstance(subToAdd.getKey(), subToAdd.getValue());
                    }
                }

                //Next, inflexible recipes.
                else {
                    Pair<Substance, Integer> subToAdd = recipe.output;

                    //Removing the inputs
                    for (Substance substance : recipe.predicateSubstances.keySet()) {
                        int currentAmount = CauldronSubstances.get(substance);
                        int newAmount = currentAmount - recipe.predicateSubstances.get(substance);
                        boolean success = CauldronSubstances.replace(substance, currentAmount, newAmount);
                        if (!success) { //i.e. there wasn't as much predicate as we thought when we calculated the amount
                            throw new ArithmeticException("Unexpected amount of predicate present");
                        }
                    }
                    for (Ingredient ingredient : recipe.predicateIngredients) {
                        boolean success = CauldronIngredients.remove(ingredient);
                        if (!success) { //i.e. an ingredient was missing
                            throw new ArithmeticException("Failed to remove ingredients");
                        }
                    }

                    addSubstance(subToAdd.getKey(), subToAdd.getValue());
                }
            }
        }
    }



    //Helper functions
    //TODO calculate water level, write overflow function, write setWaterLevel
//    public void recalculateWaterLevel() {
//        int i = calculateTotalWaterLevel;
//        if (i <= 3) {
//            setWaterLevel(i);
//        } else {
//            setWaterLevel(3);
//            overflow();
//        }
//    }


    //This method removes one item from the given stack unless the player's in Creative.
    public void removeItem(PlayerEntity player, ItemStack itemstack) {
        if (!player.abilities.instabuild) {
            itemstack.shrink(1);
        }
    }

    //This method gives the player an item, in their hand if it's empty, elsewhere if it's full,
    //or drops it on the ground if their inventory's full.
    public void giveItem(PlayerEntity player, ItemStack itemstack, Hand hand, Item item) {
        ItemStack given = new ItemStack(item);
        if (itemstack.isEmpty()) {
            player.setItemInHand(hand, given);
        } else if (!player.inventory.add(given)) {
            player.drop(given, false);
        }
    }

    //This method adds a substance to the cauldron's list. If it's already there, the amounts add.
    //Automatically calls checkRecipes afterward.
    public void addSubstance(Substance substance, int amount) {
        Integer currentValue = CauldronSubstances.putIfAbsent(substance, amount); //Puts if not already present
        if (currentValue != null) {                                     //If present,
            CauldronSubstances.put(substance, currentValue+amount);     //add currentValue to amount
        }
        checkRecipes();
    }

    public String stringSubstanceList() {
        String s = "Substances: ";
        for (Ingredient ingredient : CauldronIngredients) {
            s = s + ingredient.id + ", ";
        }
        return s;
    }

    public String stringIngredientsList() {
        String s = "Ingredients: ";
        for (Ingredient ingredient : CauldronIngredients) {
            s = s + ingredient.id + ", ";
        }
        return s;
    }



}
