package game.specialweapon;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Pokemon;
import game.enums.Element;
import game.items.Fire;

import java.util.List;

/**
 * The type Fire spin.
 * Created by:
 * @author Andrew Miller Prince (Std ID: 32795467)
 */
public class FireSpin extends SpecialWeapon implements SpecialWeaponsInterface{

    private static final String NAME= "FireSpin";
    private static final char DISPLAY_CHAR='l';
    private static final int DAMAGE=70;
    private static final String VERB="sparks";
    private static final int HITRATE =90;

    /**
     * Constructor.
     * Instantiates a new Blaze Weapon.
     */
    public FireSpin(){
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HITRATE);
        this.addCapability(Element.FIRE);
        this.addCapability(WeaponType.SPECIAL_ACTION_WEAPON);
    }

    public void specialAction(Pokemon pokemon, GameMap map) {
        Location pokeLocation = map.locationOf(pokemon);
        List<Exit> allExits = pokeLocation.getExits();
        for(Exit eachExit:allExits){
            Location fireLocation = eachExit.getDestination();
            fireLocation.addItem(new Fire());
        }
    }
}
