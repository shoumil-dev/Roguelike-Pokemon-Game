package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Element;
import game.helpers.ElementsHelper;
import game.enums.Status;
import game.pokemon.AffectionManager;
import game.actors.Pokemon;

/**
 * An Action for player to catch a nearby pokemon.
 * Created by:
 * @author Andrew Miller Prince (Std ID: 32795467)
 */
public class FeedPokemonAction extends Action {

    /**
     * The Pokemon getting fed
     */
    Pokemon pokemon;

    /**
     * The PokeFruit that us being fed
     */
    Item pokeFruit;

    public FeedPokemonAction(Pokemon pokemon,Item pokeFruit){
        this.pokemon=pokemon;
        this.pokeFruit=pokeFruit;
    }

    /**
     * Performs catch action by checking element of PokeFruit
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String result of action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        AffectionManager affectionManager=pokemon.getAffectionManager();
        int affectionPoints=affectionManager.getAffectionPoint(pokemon);
        actor.removeItemFromInventory(pokeFruit);
        boolean compatibleCheck = ElementsHelper.hasAnySimilarElements(pokemon,pokeFruit.findCapabilitiesByType(Element.class));
        if (affectionPoints<=-50){
            pokemon.removeCapability(Status.CANFEED);
            return pokemon+" dislikes you";
        }
        if(compatibleCheck){
            if(affectionPoints<100){
                int increasePoints=20;
                affectionManager.increaseAffection(pokemon,increasePoints);
                return pokemon+" Likes it! +20 affection points";
            }
        }
        else{
            int decreasePoints=10;
            affectionManager.decreaseAffection(pokemon,decreasePoints);
            return pokemon+" dislikes it! -10 affection points";
        }
        return null;
    }

    /**
     * Statement that gets printed in the terminal as an option for the player to do.
     *
     * @param actor The actor performing the action.
     * @return String Action Description
     */
    @Override
    public String menuDescription(Actor actor) {
        String ret="";
        if(pokeFruit.hasCapability(Element.FIRE)){
            ret="Fire";
        }
        else if(pokeFruit.hasCapability(Element.GRASS)){
            ret="Grass";
        }
        else if(pokeFruit.hasCapability(Element.WATER)){
            ret="Water";
        }
        return actor + " gives a "+ret+" Pokefruit to "+pokemon;
    }


}

