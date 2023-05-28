package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.enums.ItemType;
import game.items.Pokefruit;
import game.actions.FeedPokemonAction;
import game.actors.Pokemon;

import java.util.List;

/**
 * The type Feed behaviour.
 * Created by:
 * @author Shoumil Guha (Std ID: 32700660)
 */
public class FeedBehaviour implements Behaviour{
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Pokefruit feedableFruit = null;
        for (Item item : actor.getInventory()) {
            if (item.hasCapability(ItemType.POKEFRUIT)) {
                feedableFruit = (Pokefruit) item;
                break;
            }
        }

        Location here = map.locationOf(actor);
        List<Exit> allExits = here.getExits();
        for(Exit eachExit:allExits) {
            Location feedLocation = eachExit.getDestination();
            if (feedLocation.containsAnActor() && feedLocation.getActor().hasCapability(Status.HOSTILE)) {
                Actor nearbyPokemon = feedLocation.getActor();
                boolean isFeedable = nearbyPokemon.hasCapability(Status.CANFEED);
                if (isFeedable && feedableFruit != null) {
                    new Display().println(String.format("%s feeds a %s to %s", actor, "random fruit", nearbyPokemon));
                    return new FeedPokemonAction((Pokemon) nearbyPokemon, feedableFruit);
                }
            }
        }
        return null;
    }
}
