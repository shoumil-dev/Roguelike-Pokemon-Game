package game.pokemon;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.Pokemon;
import game.enums.Element;
import game.helpers.ElementsHelper;
import game.enums.Status;
import game.specialweapon.BackupWeapons;
import game.specialweapon.Bubble;
import game.specialweapon.Ember;
import game.specialweapon.SpecialWeapon;
import game.time.TimePerception;

import java.util.ArrayList;
import java.util.List;

/**
 * A class of Pokemon: Squirtle
 *
 * Created by:
 * @author Andrew Miller Prince (Std ID: 32795467)
 *
 * modified by:
 * Abdul Harith Abdul Halim  ID :32871341
 *
 */
public class Squirtle extends Pokemon implements TimePerception {
    private static final String NAME = "Squirtle";
    private static final char DISPLAY_CHAR='s';
    private static final int INTRINSIC_DAMAGE=10;
    private static final String INTRINSIC_VERB="tackle";

    /**
     * Constructor.
     * Instantiates a new Squirtle.
     */
    public Squirtle(){
        super(NAME,DISPLAY_CHAR);
        this.registerInstance();
        this.addCapability(Element.WATER);
        this.addCapability(Status.CATCHABLE);
        createSpecialWeapon();

    }

    /**
     * Overrides the getIntrinsicWeapon method from Actor class
     * @return intrinsicweapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon(){
        return new IntrinsicWeapon(INTRINSIC_DAMAGE,INTRINSIC_VERB);
    }

    /**
     * Overrides the createSpecialWeapon method from the Pokemon class.
     * Adds a Pokemon's Special Weapon to the HashMap in BackupWeapons.
     */
    @Override
    public void createSpecialWeapon() {
        List<SpecialWeapon> specialWeaponList = new ArrayList<>();
        specialWeaponList.add(new Bubble());
        BackupWeapons.getInstance().addWeapon(this,specialWeaponList);
    }

    /**
     * Overrides the checkGround method in the Pokemon class.
     * Checks if Pokemon and Ground has the same element and returns a boolean.
     * Also checks if the adjacent actor has an element of FIRE and returns a boolean.
     *
     * @param actor the pokemon
     * @param map   the gamemap
     * @return boolean
     */
    @Override
    public boolean checkGround(Actor actor, GameMap map) {
        Ground actorGround = map.locationOf(actor).getGround();
        Boolean checkGroundELe= ElementsHelper.hasAnySimilarElements(actor,actorGround.findCapabilitiesByType(Element.class));
        Location here = map.locationOf(actor);
        List<Exit> allExits= here.getExits();
        for(Exit eachExit:allExits){
            Location actorLocation =eachExit.getDestination();
            Actor otherActor= actorLocation.getActor();
            if(actorLocation.containsAnActor() && otherActor.hasCapability(Element.FIRE) ||checkGroundELe){
                return true;
            }
        }
        return false;
    }

    /**
     * hurts Squirtle by 10 points
     */

    @Override
    public void dayEffect() {
        hurt(10);
    }

    /**
     * heals Squirtle by 10 points
     */

    @Override
    public void nightEffect() {
        heal(10);

    }

    @Override
    public boolean evolvePokemon(Location pokemonLocation) {
        return false;
    }
}

