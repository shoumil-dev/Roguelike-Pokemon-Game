package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.enums.Element;
import game.helpers.ElementsHelper;
import game.enums.Status;

import java.util.List;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * Andrew Miller Prince (Std ID: 32795467)
 */
public class AttackBehaviour implements Behaviour {

    /**
     * Checks Pokemon's surrounding for a Pokemon of a different a element and attacks if one is found.
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        List<Exit> allExits= here.getExits();
        for(Exit eachExit:allExits){
            Location actorLocation = eachExit.getDestination();
            Actor otherActor= actorLocation.getActor();
            boolean checkIfPokemon = actorLocation.containsAnActor() && otherActor.hasCapability(Status.HOSTILE);
            if(checkIfPokemon && !ElementsHelper.hasAnySimilarElements(actor,otherActor.findCapabilitiesByType(Element.class))){
                return new AttackAction(otherActor, eachExit.getName());
            }
        }
        return null;
    }





}
