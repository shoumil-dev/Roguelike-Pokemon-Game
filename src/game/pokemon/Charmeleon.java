package game.pokemon;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.Pokemon;
import game.enums.Element;
import game.enums.Status;
import game.helpers.ElementsHelper;
import game.specialweapon.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Charmeleon.
 * Created by:
 * @author Andrew Miller Prince (Std ID: 32795467)
 */
public class Charmeleon extends Pokemon {
    private static final String NAME = "Charmeleon";
    private static final char DISPLAY_CHAR='C';
    private static final int INTRINSIC_DAMAGE= 10;
    private static final String INTRINSIC_VERB="scratch";

    /**
     * Instantiates a new Charmeleon.
     */
    public Charmeleon(){
        super(NAME,DISPLAY_CHAR);
        this.addCapability(Element.FIRE);
        this.addCapability(Status.CANEVOLVE);
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

    @Override
    public void createSpecialWeapon() {
        List<SpecialWeapon> specialWeaponList = new ArrayList<>();
        specialWeaponList.add(new Ember());
        specialWeaponList.add(new Blaze());
        BackupWeapons.getInstance().addWeapon(this,specialWeaponList);
    }

    @Override
    public boolean checkGround(Actor actor, GameMap map) {
        Ground actorGround = map.locationOf(actor).getGround();
        if(ElementsHelper.hasAnySimilarElements(actor,actorGround.findCapabilitiesByType(Element.class))){
            return true;
        }
        return false;
    }

    @Override
    public boolean evolvePokemon(Location pokemonLocation) {
        GameMap pokemonMap = pokemonLocation.map();
        pokemonMap.removeActor(this);
        pokemonLocation.addActor(new Charizard());
        return true;
    }
}
