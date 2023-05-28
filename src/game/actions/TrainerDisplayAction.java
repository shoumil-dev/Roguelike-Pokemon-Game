package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

import game.pokemon.AffectionManager;
import game.actors.Pokemon;
import java.util.ArrayList;
import java.util.Set;

/**
 * The type Trainer display action.
 * Created by:
 * @author Shoumil Guha (Std ID: 32700660)
 */
public class TrainerDisplayAction extends Action {
    /**
     * The Npc players.
     */
    ArrayList<Actor> npcPlayers = new ArrayList<Actor>();

    /**
     * Instantiates a new Trainer display action.
     *
     * @param npcPlayers the npc players
     */
    public TrainerDisplayAction(ArrayList<Actor> npcPlayers) {
        this.npcPlayers = npcPlayers;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String returnString = "";
        for (Actor npc : npcPlayers) {
            returnString += String.format("%s | %s,%s | inventory: %s", npc.toString(), map.locationOf(npc).x(), map.locationOf(npc).y(), npc.getInventory());
            AffectionManager affectionManager = AffectionManager.getInstance();
            Set<Pokemon> pokeList = affectionManager.getAffectionPointMap().keySet();
            for (Pokemon pokemon : pokeList) {
                if (affectionManager.getAffectionPoint(pokemon) > 0 && map.locationOf(pokemon) != null) {
                    returnString += String.format("\n- %s at %s,%s", pokemon, map.locationOf(pokemon).x(), map.locationOf(pokemon).y());
                }
            }
        }
        return returnString;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Other trainers' information";
    }

    @Override
    public String hotkey() {
        return "z";
    }
}
