package game.specialweapon;

import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Pokemon;

/**
 * The interface Special weapons interface.
 * Created by:
 * @author Andrew Miller Prince (Std ID: 32795467)
 */
public interface SpecialWeaponsInterface {

    /**
     * Special action.
     *
     * @param pokemon the pokemon
     * @param map     the map
     */
    void specialAction(Pokemon pokemon,GameMap map);
}
