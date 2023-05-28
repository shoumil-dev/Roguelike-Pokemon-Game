package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Pokemon;
import game.actions.EvolveAction;

import java.util.List;

public class EvolveBehavior implements Behaviour {


    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location locationOfActor=map.locationOf(actor);
        List<Exit> allExits = locationOfActor.getExits();
        int exitCounter = 0;
        for(Exit eachExit:allExits){
            Location evolveLocation = eachExit.getDestination();
            boolean notEmpty = evolveLocation.containsAnActor();
            if(!notEmpty){
                exitCounter++;
            }
        }
        if(exitCounter==8){
            return new EvolveAction((Pokemon) actor);
        }

        return null;
    }
}
