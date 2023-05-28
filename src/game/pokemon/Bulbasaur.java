package game.pokemon;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.Pokemon;
import game.specialweapon.BackupWeapons;
import game.enums.Element;
import game.helpers.ElementsHelper;
import game.enums.Status;
import game.specialweapon.SpecialWeapon;
import game.specialweapon.VineWhip;
import game.time.TimePerception;

import java.util.ArrayList;
import java.util.List;

/**
 * A class of Pokemon: Bulbasaur
 *
 * Created by:
 * @author Andrew Miller Prince (Std ID: 32795467)
 * modified by:
 * Abdul Harith Abdul Halim  ID :32871341
 *
 */
public class Bulbasaur extends Pokemon implements TimePerception {
    private static final String NAME = "Bulbasaur";
    private static final char DISPLAY_CHAR='b';
    private static final int INTRINSIC_DAMAGE=10;
    private static final String INTRINSIC_VERB="tackle";

    /**
     * Constructor.
     * Instantiates a new Bulbasaur.
     */
    public Bulbasaur(){
        super(NAME,DISPLAY_CHAR);
        this.addCapability(Element.GRASS);
        this.addCapability(Status.CATCHABLE);
        createSpecialWeapon();
        this.registerInstance();
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
        specialWeaponList.add(new VineWhip());
        BackupWeapons.getInstance().addWeapon(this,specialWeaponList);
    }

    /**
     * Overrides the checkGround method in the Pokemon class.
     * Checks if Pokemon and Ground has the same element and returns a boolean.
     *
     * @param actor the pokemon
     * @param map   the gamemap
     * @return boolean
     */
    @Override
    public boolean checkGround(Actor actor, GameMap map) {
        Ground actorGround = map.locationOf(actor).getGround();
        if(ElementsHelper.hasAnySimilarElements(actor,actorGround.findCapabilitiesByType(Element.class))){
            return true;
        }
        return false;
    }

    /**
     * hurts Bulbasaur by 5 points
     */

    @Override
    public void dayEffect() {
        hurt(5);
    }

    /**
     * heals Bulbasaur by 5 points
     */

    @Override
    public void nightEffect() {
        heal(5);

    }

    @Override
    public boolean evolvePokemon(Location pokemonLocation) {
        return false;
    }
}
