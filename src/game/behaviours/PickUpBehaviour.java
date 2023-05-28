package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * The type Pick up behaviour.
 * Created by:
 * @author Shoumil Guha (Std ID: 32700660)
 */
public class PickUpBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (map.locationOf(actor).getItems().size() > 0) {
            Item topItem = map.locationOf(actor).getItems().get(0);
            return new PickUpItemAction(topItem);
        }
        return null;
    }
}
