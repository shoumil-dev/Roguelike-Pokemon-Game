package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.maps.MapList;
import game.maps.TeleportAction;

/**
 * The type Door.
 * Created by:
 * @author Andrew Miller Prince (Std ID: 32795467)
 */
public class Door extends Ground {

    private MapList teleportMap;

    /**
     * Instantiates a new Door.
     *
     * @param teleportMap the teleport map
     */
    public Door(MapList teleportMap){
        super('=');
        this.teleportMap = teleportMap;

    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if (actor.hasCapability(Status.IMMUNE)){
            actions.add(new TeleportAction(teleportMap));
        }
        return actions;
    }



}