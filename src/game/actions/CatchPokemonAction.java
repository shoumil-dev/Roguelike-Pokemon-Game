package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.items.Candy;
import game.items.Pokeball;
import game.pokemon.AffectionManager;
import game.actors.Pokemon;

/**
 * An Action for player to catch a nearby pokemon.
 * Created by:
 * @author Andrew Miller Prince (Std ID: 32795467)
 */
public class CatchPokemonAction extends Action {

    /**
     * The Pokemon getting caught
     */
    private Pokemon pokemon;

    /**
     * The Pokeball in which the Pokeman gets stored
     */
    private Pokeball pokeball;

    /**
     * Constructor.
     *
     * @param pokemon the Pokemon getting caught
     */
    public CatchPokemonAction(Pokemon pokemon){
        this.pokemon=pokemon;
        this.pokeball= new Pokeball();
    }

    /**
     * Performs catch action by checking AP.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String result of action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        AffectionManager affectionManager=pokemon.getAffectionManager();
        int affectionPoints=affectionManager.getAffectionPoint(pokemon);
        if (affectionPoints>=50){
            Location candyDrop = map.locationOf(pokemon);
            candyDrop.addItem(new Candy());
            pokeball.catch_pokemon(pokemon);
            actor.addItemToInventory(pokeball);
            map.removeActor(pokemon);
            return actor+ " has caught "+pokemon;
        }
        else if (affectionPoints<=-50){
            pokemon.removeCapability(Status.CATCHABLE);
            return pokemon+" dislikes you";
        }
        else{
            affectionManager.decreaseAffection(pokemon,10);
            return "AP less than 50, "+ pokemon+" can't be captured";
        }

    }

    /**
     * Statement that gets printed in the terminal as an option for the player to do.
     *
     * @param actor The actor performing the action.
     * @return String Action Description
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor+" Captures "+pokemon;
    }
}
