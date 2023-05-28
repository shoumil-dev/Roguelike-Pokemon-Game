package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.pokemon.AffectionManager;
import game.actions.CatchPokemonAction;
import game.actors.Pokemon;
import java.util.List;

/**
 * The type Catch behaviour.
 * Created by:
 * @author Shoumil Guha (Std ID: 32700660)
 */
public class CatchBehaviour implements Behaviour{
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        List<Exit> allExits = here.getExits();
        for(Exit eachExit:allExits) {
            Location nextLocation = eachExit.getDestination();
            if (nextLocation.containsAnActor() && nextLocation.getActor().hasCapability(Status.HOSTILE)) {
                Actor pokemonActor = nextLocation.getActor();
                Pokemon nearbyPokemon = (Pokemon) pokemonActor;
                AffectionManager affectionManager = nearbyPokemon.getAffectionManager();
                int affectionPoints = affectionManager.getAffectionPoint(nearbyPokemon);
                if (affectionPoints > 50) {
                    return new CatchPokemonAction(nearbyPokemon);
                }
            }
        }
        return null;
    }
}
