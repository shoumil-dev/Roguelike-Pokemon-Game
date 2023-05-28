package game.actions;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.items.Pokeball;
import game.actors.Pokemon;

import java.util.List;

/**
 * An Action for player to summon a Pokemon.
 * Created by:
 * @author Andrew Miller Prince (Std ID: 32795467)
 */
public class SummonPokemonAction extends Action {

    /**
     * The Pokemon getting summon
     */
    Pokemon pokemon;

    /**
     * The Pokeball in which the Pokeman is stored
     */
    Pokeball pokeball;

    /**
     * Constructor.
     *
     * @param pokeball the Pokeball in which the Pokemon is in.
     */
    public SummonPokemonAction(Item pokeball){
        this.pokeball=(Pokeball) pokeball;
        this.pokemon=this.pokeball.getStoredPokemon();
    }

    /**
     * Adds Pokemon to map and removes Pokeball from the player's inventory
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(pokeball);
        Location here = map.locationOf(actor);
        List<Exit> allExits = here.getExits();
        for(Exit eachExit:allExits){
            Location summonLocation = eachExit.getDestination();
            Boolean canSummon =summonLocation.canActorEnter(pokemon);
            if(canSummon){
                summonLocation.addActor(pokemon);
                return "I choose you... "+pokemon+"!";
            }
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
        return actor+" Summons "+pokemon;
    }
}

