package game.pokemon;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.Status;
import game.actors.Pokemon;
import game.specialweapon.BackupWeapons;
import game.enums.Element;
import game.helpers.ElementsHelper;
import game.specialweapon.Ember;
import game.specialweapon.SpecialWeapon;
import game.time.TimePerception;

import java.util.ArrayList;
import java.util.List;

/**
 * A class of Pokemon: Charmander
 *
 * Created by:
 * @author Andrew Miller Prince (Std ID: 32795467)
 *
 * modified by:
 * Abdul Harith Abdul Halim  ID : 32871341
 *
 */

public class Charmander extends Pokemon implements TimePerception{
    private static final String NAME = "Charmander";
    private static final char DISPLAY_CHAR='c';
    private static final int INTRINSIC_DAMAGE= 10;
    private static final String INTRINSIC_VERB="scratch";

    /**
     * Constructor.
     * Instantiates a new Charmander.
     */
    public Charmander() {
        super(NAME,DISPLAY_CHAR);
        this.addCapability(Element.FIRE);
        this.addCapability(Status.CATCHABLE);
        this.addCapability(Status.CANEVOLVE);
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
        specialWeaponList.add(new Ember());
        BackupWeapons.getInstance().addWeapon(this,specialWeaponList);
    }

    /**
     * Overrides the checkGround method in the Pokemon class.
     * Checks if Pokemon and Ground has the same element and returns a boolean.
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
     * heals charmander by 10 points
     */


    @Override
    public void dayEffect() {
        heal(10);
    }

    /**
     * hurts Charmander by 10 points
     */

    @Override
    public void nightEffect() {
        hurt(10);
    }

    /**
     * performs day or night affect on Charmander
     */

    @Override
    public boolean evolvePokemon(Location pokemonLocation) {
        GameMap pokemonMap = pokemonLocation.map();
        pokemonMap.removeActor(this);
        pokemonLocation.addActor(new Charmeleon());
        return true;
    }


}
